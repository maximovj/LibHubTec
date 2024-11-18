package com.github.maximovj.libhubtec.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.maximovj.libhubtec.user.UserInfo;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;


@Component
public class JwtService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    // Generate token with given user name
    public String generateToken(Long id) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, id);
    }

    public String generateTokenWithClaimns(Map<String, Object> claims, Long subject) {
        return createToken(claims, subject);
    }

    public String refreshToken(String token) {
        try {

            if(this.validateToken(token)){
                // Validar token
                Claims claims = this.extractAllClaims(token);
                // Crear nuevo token
                return this.createToken(claims, Long.parseLong(claims.getSubject()));
            }
        } catch (SignatureException | ExpiredJwtException e) {
            // Si el token es inválido o expirado, lanza una excepción o maneja el error
            //throw new RuntimeException("Token inválido o expirado");
        }
        return null;
    }

    // Crear un JWT token con claims y subject (usaername / id) especificos
    private String createToken(Map<String, Object> claims, Long id) {
        return this.hiddenToken(Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token valid for 30 minutes
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact());
    }

    // Obtener una firma clave para JWT token
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extraer el subject (usaername / id) desde el token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extraer la fecha de expiración desde el token 
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extraer un claim desde el token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractClaim(String token, String claim_name) {
        Claims claims = extractAllClaims(token);
        return claims.get(claim_name, String.class);
    }

    // Extraer todos los `claims` desde el token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(this.showToken(token))
                .getBody();
    }

    // Verifica si token está expirado
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Válida que el token sea un userDetails y la expiración
    public Boolean verifyTokenWithUsername(String token, Optional<UserInfo> userInfo) {
        // Extraer el subject (usaername / id) desde el token
        final Long username = Long.parseLong(extractUsername(token));
        return (username.equals(userInfo.get().getId()) && !isTokenExpired(token));
    }

    // Validar el token existe, usando un token previamente generado
    public Boolean validateToken(String token) {
        try {
            final String username = extractUsername(token);
            final Claims claims = extractAllClaims(token);
            // Si se puede obtener los claims significa que el token es válido.
            return (username.length() > 0 && !isTokenExpired(token) && claims != null);
        } catch (Exception e) {
            // Maneja excepciones como un token mal formado, expirado, etc.
            this.log.error("JwtService:::validateToken | Error al validar el token: " + e.getMessage());
            return false; // Retorna falso en caso de error en la validación
        }
    }

    // Mostrar token original
    private String showToken(String token)
    {
        String token_decode = this.decode(token);
        String token_reverse = this.reverse(token_decode);
        return token_reverse;
    }
    
    // Ocultar token original
    private String hiddenToken(String token)
    {
        String token_reverse = this.reverse(token);
        String token_encode = this.encode(token_reverse);
        return token_encode;
    }

    // Codificar String a Base64
    private String encode(String input) 
    {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    // Descifrar Base64 a String 
    private String decode(String base64Encoded) 
    {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
        return new String(decodedBytes);
    }

    // Invertir String
    private String reverse(String input) 
    {
        return new StringBuilder(input).reverse().toString();
    }

}
