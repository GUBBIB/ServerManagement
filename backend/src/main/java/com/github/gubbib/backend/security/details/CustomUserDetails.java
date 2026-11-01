package com.github.gubbib.backend.security.details;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final LoginUserDTO user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<String> roles = user.roles();
        String status = user.status();

        if(status.equals("PENDING")){
            return List.of(new SimpleGrantedAuthority("ROLE_PENDING"));
        }

        if (!"ACTIVE".equals(status)) {
            return List.of();
        }

        if(roles == null){
            return List.of();
        }

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
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
        return (user.user_id());
    }
}
