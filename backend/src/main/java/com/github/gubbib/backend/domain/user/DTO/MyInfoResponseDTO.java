package com.github.gubbib.backend.domain.user.DTO;

import java.util.List;

public record MyInfoResponseDTO(
        Long user_id,
        String email,
        String name,
        String status,
        String phone,
        List<String> roles
) {
}
