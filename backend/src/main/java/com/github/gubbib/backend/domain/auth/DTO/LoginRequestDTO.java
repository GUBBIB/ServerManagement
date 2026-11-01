package com.github.gubbib.backend.domain.auth.DTO;

public record LoginRequestDTO(
        String email,
        String password
) {
}
