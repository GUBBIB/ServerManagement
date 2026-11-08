package com.github.gubbib.backend.domain.user.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.role.service.RoleService;
import com.github.gubbib.backend.domain.user.mapper.UserMapper;
import com.github.gubbib.backend.exception.BusinessException;
import com.github.gubbib.backend.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;
    private final SqlSessionTemplate batchSqlSessionTemplate;

    public UserServiceImpl(
            UserMapper userMapper,
            SqlSessionTemplate batchSqlSessionTemplate
    ) {
        this.userMapper = userMapper;
        this.batchSqlSessionTemplate = batchSqlSessionTemplate;
    }

    /**
     * [ 활성화 | 대기 유저 조회 By email ]
     *
     * @param email
     * @return
     */
    @Override
    public LoginUserDTO selectActiveAndPendingUserByEmail(String email) {
        return userMapper.selectActiveAndPendingUserByEmail(email);
    }

    /**
     * [ 회원 역할 조회 - USER_ID ]
     *
     * @param userId
     * @return
     */
    @Override
    public LoginUserDTO selectUserByUserId(Long userId) {
        return userMapper.selectUserByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * [ 회원 조회 - EMAIL ]
     *
     * @param email
     * @return
     */
    @Override
    public LoginUserDTO selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
