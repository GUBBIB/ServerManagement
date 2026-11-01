package com.github.gubbib.backend.domain.manage.user.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupRejectRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserManageServiceImpl implements UserManageService {


    @Override
    @Transactional
    public LoginUserDTO signupApprove(SignupApproveRequestDTO requestDTO) {
        return null;
    }

    @Override
    public LoginUserDTO signupReject(SignupRejectRequestDTO requestDTO) {
        return null;
    }
}
