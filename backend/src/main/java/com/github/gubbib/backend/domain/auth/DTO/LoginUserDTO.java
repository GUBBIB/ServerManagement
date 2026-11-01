package com.github.gubbib.backend.domain.auth.DTO;

import java.util.List;

public record LoginUserDTO(
        Long user_id,
        String email,
        String password,
        String name,
        List<String> roles,
        String status
) {
}
