package com.github.gubbib.backend.domain.user.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;

import java.util.HashMap;

public interface UserService {
    LoginUserDTO selectActiveAndPendingUserByEmail(String email);
    LoginUserDTO selectUserByUserId(Long userId);
    LoginUserDTO selectUserByEmail(String email);
}
