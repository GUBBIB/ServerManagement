package com.github.gubbib.backend.domain.manage.role.DTO;

public record RoleListResponseDTO(
        String role_code,
        String role_name,
        String description
) {
}
