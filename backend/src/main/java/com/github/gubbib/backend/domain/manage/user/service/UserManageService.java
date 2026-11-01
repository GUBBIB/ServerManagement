package com.github.gubbib.backend.domain.manage.user.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupRejectRequestDTO;

public interface UserManageService {

    LoginUserDTO signupApprove(SignupApproveRequestDTO requestDTO);
    LoginUserDTO signupReject(SignupRejectRequestDTO requestDTO);
}
