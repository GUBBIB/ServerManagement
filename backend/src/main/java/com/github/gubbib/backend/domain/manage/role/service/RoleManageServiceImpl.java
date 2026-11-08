package com.github.gubbib.backend.domain.manage.role.service;

import com.github.gubbib.backend.domain.manage.role.DTO.RoleSaveRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleResponseDTO;
import com.github.gubbib.backend.domain.manage.role.mapper.RoleManageMapper;
import com.github.gubbib.backend.exception.BusinessException;
import com.github.gubbib.backend.exception.ErrorCode;
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

    @Override
    public RoleResponseDTO selectRoleByRoleCode(String roleCode){
        return roleManageMapper.selectRoleByRoleCode(roleCode)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));
    }

    /**
     * [ 역할 목록 조회 ]
     *
     * @param userDetails
     * @return
     */
    @Override
    public List<RoleResponseDTO> selectRoleList(CustomUserDetails userDetails) {
        return roleManageMapper.selectRoleList(userDetails);
    }

    /**
     * [ 역할 추가 ]
     *
     * @param userDetails
     * @param requestDTO
     * @return
     */
    @Override
    public void insertRole(RoleSaveRequestDTO requestDTO, CustomUserDetails userDetails) {
        roleManageMapper.insertRole(requestDTO, userDetails);
    }

    /**
     * [ 역할 수정 ]
     *
     * @param userDetails
     * @param roleCode
     * @param requestDTO
     * @return
     */
    @Override
    public void updateRole(RoleSaveRequestDTO requestDTO, String roleCode, CustomUserDetails userDetails) {
        RoleResponseDTO roleResponseDTO = selectRoleByRoleCode(roleCode);

        roleManageMapper.updateRole(requestDTO, roleCode, userDetails);
    }


    @Override
    public void deleteRole(String roleCode, CustomUserDetails userDetails) {
        RoleResponseDTO roleResponseDTO = selectRoleByRoleCode(roleCode);

        roleManageMapper.deleteRole(roleCode, userDetails);
    }
}