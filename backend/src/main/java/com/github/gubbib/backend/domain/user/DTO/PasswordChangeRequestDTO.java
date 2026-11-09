package com.github.gubbib.backend.domain.user.DTO;

import jakarta.validation.constraints.NotBlank;

public record PasswordChangeRequestDTO(

        @NotBlank(message = "현재 비밀번호를 입력해 주세요.")
        String currentPassword,

        @NotBlank(message = "새 비밀번호를 입력해 주세요.")
        String newPassword

) {
}
