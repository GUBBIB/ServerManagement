package com.github.gubbib.backend.security.details;

import com.github.gubbib.backend.domain.auth.DTO.AuthUserDTO;
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

    private final AuthUserDTO user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String role = user.role();
        String status = user.status();

        if(status.equals("PENDING")){
            return List.of(new SimpleGrantedAuthority("ROLE_PENDING"));
        }

        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role)
        );
    }

    @Override
    public @Nullable String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.email();
    }

    public Long getId(){
        return Long.parseLong(user.user_id());
    }
}
