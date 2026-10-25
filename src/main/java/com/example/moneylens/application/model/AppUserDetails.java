package com.example.moneylens.application.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
public class AppUserDetails implements UserDetails {
    private String userId;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
}