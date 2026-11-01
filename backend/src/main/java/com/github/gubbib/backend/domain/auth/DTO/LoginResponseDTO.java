package com.github.gubbib.backend.domain.auth.DTO;

public record LoginResponseDTO(
        String status,
        String message,
        String accessToken,
        Long expiresInMillis
) {
}
