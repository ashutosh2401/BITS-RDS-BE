//package com.resume.userservice.auth.filter;
//
////import com.resume.userservice.auth.security.MyUserDetails;
//import com.resume.userservice.auth.service.impl.MyUserDetailsService;
//import com.resume.userservice.auth.util.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain) throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//
//        String email = null;
//        String jwt = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwt = authHeader.substring(7);
//            email = jwtUtil.extractUsername(jwt);
//        }
//
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            MyUserDetails userDetails = this.userDetailsService.loadUserByEmail(email);
//            if (jwtUtil.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken token =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(token);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
