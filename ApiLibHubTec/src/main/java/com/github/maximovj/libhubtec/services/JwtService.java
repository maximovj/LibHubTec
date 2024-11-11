package com.github.maximovj.libhubtec.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.github.maximovj.libhubtec.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "M1ClaveS3cretaLarg4ParaJWT2023SeguraLarg4ParaJWT2023Segura";

	public String getToken(UserDetails user) {
		return getToken(new HashMap<>(), user);
	}

	private String getToken(Map<String, Object> extraClaims, UserDetails user) {
		return Jwts
				.builder()
				.claims(extraClaims)
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() *100 * 60 * 24))
				.signWith(getKey())
				.compact();
	}
	
	private Key getKey () {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public SecretKey getSecretKey() {
        // Define una clave secreta en formato de cadena (asegúrate de usar una clave segura)
        String secret = SECRET_KEY;
        
        // Convierte la clave secreta en bytes
        byte[] keyBytes = secret.getBytes();
        
        // Crea la `SecretKey` usando `SecretKeySpec` y el algoritmo HMAC SHA-256
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
    }

	public String getUsernameFromToken(String token) {
		return this.getClaim(token, Claims::getSubject);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		final String username = this.getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !this.isTokenExpirated(token) );
	}
	
	private Claims getAllClaims(String token) {
		return Jwts.parser().setSigningKey(getKey()).build().parseSignedClaims(token).getPayload();
	}
	
	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) 
	{
		final Claims claimns = this.getAllClaims(token);
		return  claimsResolver.apply(claimns);
	}
	
	private Date getExpiration(String token) 
	{
		return this.getClaim(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpirated(String token) {
		return getExpiration(token).before(new Date());
	}
	
	
}
