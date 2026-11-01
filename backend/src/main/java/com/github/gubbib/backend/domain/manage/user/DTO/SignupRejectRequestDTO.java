package com.github.gubbib.backend.domain.manage.DTO;

import jakarta.validation.constraints.NotBlank;

public record SignupRejectRequestDTO(

        @NotBlank(message = "user_id를 포함해주세요.")
        Long user_id,

        @NotBlank(message = "반려 사유를 입력해주세요.")
        String reason
) {
}
