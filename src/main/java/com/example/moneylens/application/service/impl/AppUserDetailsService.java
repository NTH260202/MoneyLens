package com.example.moneylens.application.service.impl;

import com.example.moneylens.application.model.AppUserDetails;
import com.example.moneylens.domain.entity.User;
import com.example.moneylens.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository users;

    public AppUserDetailsService(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        String id = identifier.trim().toLowerCase(Locale.ROOT);
        User user = users.findByUsernameOrEmail(id, id).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        AppUserDetails authenticatedUser = new AppUserDetails();
        authenticatedUser.setUsername(user.getUsername());
        authenticatedUser.setUserId(user.getId());
        authenticatedUser.setPassword(user.getPasswordHash());
        authenticatedUser.setEmail(user.getEmail());

        return authenticatedUser;
    }
}
