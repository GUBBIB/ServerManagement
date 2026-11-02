package com.github.gubbib.backend.domain.manage.user.DTO;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public record UserStatusUpdateRequestDTO(

        @NotBlank(message = "status를 포함해주세요.")
        String status,

        String reason
) {

        @AssertTrue(message = "정지 상태로 변경할 때는 사유를 입력해주세요")
        public boolean isReasonValid() {
                return !"SUSPENDED".equals(status)
                        || (reason != null && !reason.isBlank());
        }
}