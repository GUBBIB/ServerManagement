package com.github.gubbib.backend.domain.manage.user.DTO;

import jakarta.validation.constraints.NotBlank;

public record SignupApproveRequestDTO(

        @NotBlank(message = "user_id를 포함해주세요.")
        Long user_id
) {
}
