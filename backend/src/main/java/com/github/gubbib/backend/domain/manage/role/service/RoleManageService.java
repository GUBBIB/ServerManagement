package com.github.gubbib.backend.domain.manage.role.service;

import com.github.gubbib.backend.common.DTO.PageResponseDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleSaveRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleResponseDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import jakarta.validation.Valid;

public interface RoleManageService {

    PageResponseDTO<RoleResponseDTO> selectRoleList(CustomUserDetails userDetails, int page, int perPage, String searchType, String keyword, String useYn);
    void insertRole(@Valid RoleSaveRequestDTO requestDTO, CustomUserDetails userDetails);
    void updateRole(@Valid RoleSaveRequestDTO requestDTO, String roleCode, CustomUserDetails userDetails);
    RoleResponseDTO selectRoleByRoleCode(String roleCode);
    void deleteRole(String roleCode, CustomUserDetails userDetails);
}
