package com.github.gubbib.backend.domain.manage.role.DTO;

import jakarta.validation.constraints.NotBlank;

public record RoleCreateRequestDTO(

        @NotBlank(message = "역할 코드를 포함해주세요.")
        String role_code,

        @NotBlank(message = "역할명을 포함해주세요.")
        String role_name,

        @NotBlank(message = "역할 설명을 포함해주세요.")
        String description,

        String use_yn
) {
}
