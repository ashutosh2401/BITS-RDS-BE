//package com.resume.authservice.config;
//
//import com.resume.authservice.repository.UserRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        userRepository.findByEmail(username);
//        return User.builder().username("").roles(null).password("").build();
//
//    }
//}
