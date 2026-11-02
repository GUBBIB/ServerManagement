package com.github.gubbib.backend.domain.manage.role.mapper;

import com.github.gubbib.backend.domain.manage.role.DTO.RoleListResponseDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleManageMapper {

    List<RoleListResponseDTO> selectRoleList(@Param("USER_DETAILS") CustomUserDetails userDetails);
}