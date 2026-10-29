package com.github.gubbib.backend.domain.auth.service;

import com.github.gubbib.backend.domain.auth.DTO.AuthUserDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;

public interface AuthService {
    void signupRequest(SignupRequestDTO requestDTO);
    AuthUserDTO selectActiveAndPendingUserByEmail(String email);
}
