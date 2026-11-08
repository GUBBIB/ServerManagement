package com.github.gubbib.backend.domain.manage.user.DTO;

public record SignupUserListDTO(
        Long user_id,
        String email,
        String name,
        String phone,
        String status
) {
}
