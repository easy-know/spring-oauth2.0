//package com.oauth.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * Description :
// *
// * @author leejinho
// * @version 1.0
// */
//
//@RequiredArgsConstructor
//public class DeveloperAuthenticationProvider implements AuthenticationProvider {
//
//    private final DeveloperUserDetailService developerDetailService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
