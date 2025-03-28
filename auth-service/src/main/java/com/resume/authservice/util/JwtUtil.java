package com.resume.authservice.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "ea316a3458c547f7a24722dd852e83a4c3305c5424f185cc97851df927037730";
    private final long EXPIRATION_TIME = 86400000; // 24 hours

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
//    private SecretKey getSigningKey() {
//        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }

//    public String generateToken(String email) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, email);
////        return Jwts.builder()
////                .setSubject(email)
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
////                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
////                .compact();
//    }

//    private String createToken(Map<String, Object> claims, String userName) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userName)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token valid for 30 minutes
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }

//    public String extractEmail(String token) {
//        return Jwts.parser()
//                .setSigningKey(getSigningKey())  // Use parserBuilder() instead of parser()
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }

//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser()
//                    .setSigningKey(getSigningKey())
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
