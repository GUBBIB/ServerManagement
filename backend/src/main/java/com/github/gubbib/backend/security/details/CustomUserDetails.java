package com.github.gubbib.backend.security.details;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final HashMap<String, Object> user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String role = user.get("ROLE").toString();
        String status =  user.get("STATUS").toString();

        if(status.equals("PENDING")){
            return List.of(new SimpleGrantedAuthority("ROLE_PENDING"));
        }

        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role)
        );
    }

    @Override
    public @Nullable String getPassword() {
        return user.get("PASSWORD").toString();
    }

    @Override
    public String getUsername() {
        return user.get("EMAIL").toString();
    }

    public Long getId(){
        return Long.parseLong(user.get("ID").toString());
    }
}
