//package com.resume.userservice.auth.util;
//
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class JwtTokenProvider {
//    private final String secretKey = "your-secret-key";
//
//    public String createToken(String email, List<String> roles) {
//        Claims claims = (Claims) Jwts.claims().setSubject(email);
//        claims.put("roles", roles);
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + 3600000); // 1 hour
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    public String getEmail(String token) {
//        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//}
