package com.upload.demo.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.upload.demo.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class MainUser {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private MainUser(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();

        this.authorities = user.getRoles().stream().map(role -> {
            return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
        }).collect(Collectors.toList());
    }

    public static MainUser create(User user){
        return new MainUser(user);
    }
}