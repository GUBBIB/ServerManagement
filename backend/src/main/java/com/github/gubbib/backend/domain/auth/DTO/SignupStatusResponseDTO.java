package com.github.gubbib.backend.domain.auth.DTO;

import java.util.List;

public record SignupStatusResponseDTO(
        Long user_id,
        String email,
        String name,
        String status,
        String phone,
        String rejection_reason
) {
}
