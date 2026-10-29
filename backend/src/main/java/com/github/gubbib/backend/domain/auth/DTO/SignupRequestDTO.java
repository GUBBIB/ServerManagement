package com.github.gubbib.backend.domain.auth.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupRequestDTO(

        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        @Size(max = 255, message = "이메일은 255자 이하여야 합니다.")g
        String email,

        @NotBlank(message = "비밀번호를 입력해 주세요")
        String password,

        @NotBlank(message = "이름을 입력해 주세요.")
        @Size(max = 45, message = "이름은 45자 이하여야 합니다.")
        String name,

        @NotBlank(message = "휴대폰번호를 입력해 주세요.")
        @Pattern(
                regexp = "^010[0-9]{8}$",
                message = "휴대폰번호는 010으로 시작하는 숫자 11자리여야 합니다."
        )
        String phone

) {
}
