package com.github.gubbib.backend.domain.manage.role.service;

import com.github.gubbib.backend.domain.manage.role.DTO.RoleSaveRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleResponseDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import jakarta.validation.Valid;

import java.util.List;

public interface RoleManageService {

    List<RoleResponseDTO> selectRoleList(CustomUserDetails userDetails);
    void insertRole(@Valid RoleSaveRequestDTO requestDTO, CustomUserDetails userDetails);
    void updateRole(@Valid RoleSaveRequestDTO requestDTO, String roleCode, CustomUserDetails userDetails);
    RoleResponseDTO selectRoleByRoleCode(String roleCode);
    void deleteRole(String roleCode, CustomUserDetails userDetails);
}
