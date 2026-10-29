package com.github.gubbib.backend.domain.auth.mapper;

import com.github.gubbib.backend.domain.auth.DTO.AuthUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {
    boolean existsByEmail(@Param("EMAIL") String email);
    AuthUserDTO selectActiveAndPendingUserByEmail(@Param("EMAIL") String email);
}
