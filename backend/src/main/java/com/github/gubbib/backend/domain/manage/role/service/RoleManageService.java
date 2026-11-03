package com.github.gubbib.backend.domain.manage.role.service;

import com.github.gubbib.backend.domain.manage.role.DTO.RoleCreateRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleListResponseDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import jakarta.validation.Valid;

import java.util.List;

public interface RoleManageService {

    List<RoleListResponseDTO> selectRoleList(CustomUserDetails userDetails);
    void insertRole(@Valid RoleCreateRequestDTO requestDTO, CustomUserDetails userDetails);
}
