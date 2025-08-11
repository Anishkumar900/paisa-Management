package com.paisaManagement.configuration;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Service
public class JWTService {

    private static final String SECRET_KEY = "my-super-secret-key-1234567890123456";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    private final long expirationMS=864000000;

    public String generateJWTToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationMS))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (RuntimeException e) {
            return null;
        }
    }

    public boolean isTokenExpired(String token){
        try{
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        } catch (RuntimeException e) {
            return true;
        }
    }

    public boolean isTokenValid(String token){
        return extractUsername(token) !=null && !isTokenExpired(token);
    }
}
