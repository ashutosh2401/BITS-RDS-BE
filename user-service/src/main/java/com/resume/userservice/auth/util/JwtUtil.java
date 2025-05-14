package com.resume.userservice.auth.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secret_key;
    private final long EXPIRATION_TIME = 3600000; // 1 hour

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, secret_key)
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


    public String createToken(String email, List<String> roles) {
        Claims claims = (Claims) Jwts.claims().setSubject(email);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secret_key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret_key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
