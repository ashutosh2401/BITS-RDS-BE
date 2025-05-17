//package com.resume.userservice.auth.service.impl;
//
////import com.resume.userservice.auth.security.MyUserDetails;
//import com.resume.userservice.user.entity.User;
//import com.resume.userservice.user.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        return new MyUserDetails(user);
//    }
//
//    public MyUserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
//
//        return new MyUserDetails(user);
//    }
//}
//
//
//
