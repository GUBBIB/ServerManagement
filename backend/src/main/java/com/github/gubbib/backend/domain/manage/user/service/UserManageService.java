package com.github.gubbib.backend.domain.manage.user.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupRejectRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.UserStatusUpdateRequestDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;

public interface UserManageService {

    LoginUserDTO signupApprove(CustomUserDetails userDetails, SignupApproveRequestDTO requestDTO);
    LoginUserDTO signupReject(CustomUserDetails userDetails, SignupRejectRequestDTO requestDTO);
    void updateUserStatus(CustomUserDetails userDetails, Long userId, UserStatusUpdateRequestDTO requestDTO);
}
