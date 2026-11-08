package com.github.gubbib.backend.domain.role.mapper;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RoleMapper {
    List<String> selectUserRolesByEmail(@Param("EMAIL") String email);
    List<String> selectUserRolesByUserId(@Param("USER_ID") Long userId);
}
