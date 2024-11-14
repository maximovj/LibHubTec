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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	// Replace this with a secure key in a real application, ideally fetched from environment variables
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    // Generate token with given user name
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    public String refreshToken(String token) {
        try {

            if(this.validateToken(token)){
                // Validar token
                Claims claims = this.extractAllClaims(token);
                // Crear nuevo token
                return this.createToken(claims, claims.getSubject());
            }
        } catch (SignatureException | ExpiredJwtException e) {
            // Si el token es inválido o expirado, lanza una excepción o maneja el error
            //throw new RuntimeException("Token inválido o expirado");
        }
        return null;
    }

    // Create a JWT token with specified claims and subject (user name)
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token valid for 30 minutes
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Get the signing key for JWT token
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate the token against user details and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        this.log.info("JwtService:::validateToken | " .concat(username));
        this.log.info("JwtService:::validateToken | " .concat(userDetails.getUsername()));
        final boolean isValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        this.log.info("JwtService:::validateToken | " + isValid);
        return isValid;
    }

    // Validar el token existe, usando un token previamente generado
    public Boolean validateToken(String token) {
        try {
            final String username = extractUsername(token);
            final Claims claims = extractAllClaims(token);
            this.log.info("JwtService:::validateToken | username = " + username);
    
            // Valida que el token no haya expirado y que el nombre de usuario sea válido
            final boolean isValid = (username.length() > 3 && !isTokenExpired(token) && claims != null);
            this.log.info("JwtService:::validateToken | isValid = " + isValid);
            
            return isValid;
    
        } catch (Exception e) {
            // Maneja excepciones como un token mal formado, expirado, etc.
            this.log.error("JwtService:::validateToken | Error al validar el token: " + e.getMessage());
            return false; // Retorna falso en caso de error en la validación
        }
    }


}
