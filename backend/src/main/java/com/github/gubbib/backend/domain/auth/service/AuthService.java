package com.github.gubbib.backend.domain.auth.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginResponseDTO;
import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.auth.DTO.LoginRequestDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;

public interface AuthService {
    void signupRequest(SignupRequestDTO requestDTO);
    LoginUserDTO selectActiveAndPendingUserByEmail(String email);
    LoginResponseDTO login(LoginRequestDTO requestDTO);
}
