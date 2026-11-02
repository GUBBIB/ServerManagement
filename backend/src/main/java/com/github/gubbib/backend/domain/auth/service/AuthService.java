package com.github.gubbib.backend.domain.auth.service;

import com.github.gubbib.backend.domain.auth.DTO.*;
import com.github.gubbib.backend.security.details.CustomUserDetails;

public interface AuthService {
    void signupRequest(SignupRequestDTO requestDTO);
    LoginUserDTO selectActiveAndPendingUserByEmail(String email);
    LoginResponseDTO login(LoginRequestDTO requestDTO);
    SignupStatusResponseDTO selectSignupStatus(CustomUserDetails userDetails);
}
