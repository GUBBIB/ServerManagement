package com.github.gubbib.backend.domain.role.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.role.mapper.RoleMapper;
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
public class RoleServiceImpl implements RoleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoleMapper roleMapper;
    private final SqlSessionTemplate batchSqlSessionTemplate;

    public RoleServiceImpl(
            RoleMapper roleMapper,
            SqlSessionTemplate batchSqlSessionTemplate
    ) {
        this.roleMapper = roleMapper;
        this.batchSqlSessionTemplate = batchSqlSessionTemplate;
    }

    /**
     * [ 회원 역할 조회 - USER_ID ]
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> selectUserRolesByUserId(Long userId) {
        return roleMapper.selectUserRolesByUserId(userId);
    }

    /**
     * [ 회원 역할 조회 - EMAIL ]
     *
     * @param email
     * @return
     */
    @Override
    public List<String> selectUserRolesByEmail(String email) {
        return roleMapper.selectUserRolesByEmail(email);
    }

}
