package com.github.gubbib.backend.domain.role.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;

import java.util.List;

public interface RoleService {
    List<String> selectUserRolesByEmail(String email);
    List<String> selectUserRolesByUserId(Long userId);
}
