package com.github.gubbib.backend.domain.user.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.user.mapper.UserMapper;
import com.github.gubbib.backend.exception.BusinessException;
import com.github.gubbib.backend.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper usermapper;
    private final SqlSessionTemplate batchSqlSessionTemplate;

    public UserServiceImpl(
            UserMapper usermapper,
            SqlSessionTemplate batchSqlSessionTemplate
    ) {
        this.usermapper = usermapper;
        this.batchSqlSessionTemplate = batchSqlSessionTemplate;
    }

    @Override
    public LoginUserDTO selectUserByUserId(Long userId) {
        return usermapper.selectUserByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public LoginUserDTO selectUserByEmail(String email) {
        return usermapper.selectUserByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
