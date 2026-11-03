package com.github.gubbib.backend.domain.manage.role.service;

import com.github.gubbib.backend.domain.manage.role.DTO.RoleCreateRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleListResponseDTO;
import com.github.gubbib.backend.domain.manage.role.mapper.RoleManageMapper;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
/**
 * ADMIN 이거나 MANAGER와 USER_MANAGER 역할을 모두 보유 해야 한다.
 */
@PreAuthorize("""
        hasRole('ADMIN') or
        (hasRole('MANAGER') and hasRole('ROLE_MANAGER'))
        """)
public class RoleManageServiceImpl implements RoleManageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 일반 Mapper
    private final RoleManageMapper roleManageMapper;
    // Batch Mapper
    private final SqlSessionTemplate batchSqlSessionTemplate;

    public RoleManageServiceImpl(
            RoleManageMapper roleManageMapper,
            SqlSessionTemplate batchSqlSessionTemplate
    ) {
        this.roleManageMapper = roleManageMapper;
        this.batchSqlSessionTemplate = batchSqlSessionTemplate;
    }

    /**
     * [ 역할 목록 조회 ]
     *
     * @param userDetails
     * @return
     */
    @Override
    public List<RoleListResponseDTO> selectRoleList(CustomUserDetails userDetails) {
        return roleManageMapper.selectRoleList(userDetails);
    }

    @Override
    public void insertRole(RoleCreateRequestDTO requestDTO, CustomUserDetails userDetails) {
        roleManageMapper.insertRole(requestDTO, userDetails);
    }
}