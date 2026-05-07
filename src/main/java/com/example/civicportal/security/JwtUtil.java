package com.example.civicportal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ Secret Key
    private final Key key =
            Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ✅ Generate JWT Token
    public String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("username", username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24 // 24 hours
                        )
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract Username
    public String extractUsername(String token) {

        return extractClaims(token).getSubject();
    }

    // ✅ Extract Role
    public String extractRole(String token) {

        return extractClaims(token)
                .get("role", String.class);
    }

    // ✅ Extract All Claims
    private Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Validate Token
    public boolean validateToken(String token) {

        try {

            extractClaims(token);
            return true;

        } catch (Exception e) {

            return false;
        }
    }
}