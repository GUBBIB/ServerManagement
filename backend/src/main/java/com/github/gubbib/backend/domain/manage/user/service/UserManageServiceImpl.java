package com.github.gubbib.backend.domain.manage.user.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupRejectRequestDTO;
import com.github.gubbib.backend.domain.manage.user.mapper.UserManageMapper;
import com.github.gubbib.backend.domain.user.service.UserService;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
/**
 * ADMIN 이거나 MANAGER와 USER_MANAGER 역할을 모두 보유 해야 한다.
 */
@PreAuthorize("""
        hasRole('ADMIN') or
        (hasRole('MANAGER') and hasRole('USER_MANAGER'))
        """)
public class UserManageServiceImpl implements UserManageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    // 일반 Mapper
    private final UserManageMapper userManageMapper;
    // Batch Mapper
    private final SqlSessionTemplate batchSqlSessionTemplate;

    public UserManageServiceImpl(
            UserService userService,
            UserManageMapper userManageMapper,
            SqlSessionTemplate batchSqlSessionTemplate
    ) {
        this.userService = userService;
        this.userManageMapper = userManageMapper;
        this.batchSqlSessionTemplate = batchSqlSessionTemplate;
    }

    @Override
    @Transactional
    public LoginUserDTO signupApprove(CustomUserDetails userDetails, SignupApproveRequestDTO requestDTO) {
        LoginUserDTO user = userService.selectUserByUserId(requestDTO.user_id());

        userManageMapper.updateSignupApprove(requestDTO, userDetails);

        return user;
    }

    @Override
    @Transactional
    public LoginUserDTO signupReject(CustomUserDetails userDetails, SignupRejectRequestDTO requestDTO) {
        LoginUserDTO user = userService.selectUserByUserId(requestDTO.user_id());

        userManageMapper.updateSignupReject(requestDTO, userDetails);

        return user;
    }
}
