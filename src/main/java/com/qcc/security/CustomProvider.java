package com.qcc.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomProvider implements AuthenticationProvider {
    private CustomUserDetailsService userDetailsService;
    public CustomUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication cache = SecurityContextHolder.getContext().getAuthentication();
        if (cache != null && authentication instanceof AccountToken && cache instanceof AccountToken) {
            return cache;
        }
        String userName = (String) authentication.getPrincipal();
        String password  = (String) authentication.getCredentials();
        return userDetailsService.loadAccount(userName, password);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
