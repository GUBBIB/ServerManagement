package com.github.gubbib.backend.domain.auth.DTO;

public record AuthUserDTO(
        String user_id,
        String email,
        String password,
        String name,
        String role,
        String status
) {
}
