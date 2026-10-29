package com.github.gubbib.backend.domain.auth.DTO;

public record SignupRequestDTO(
        String email,
        String password,
        String name,
        String phone

) {
}
