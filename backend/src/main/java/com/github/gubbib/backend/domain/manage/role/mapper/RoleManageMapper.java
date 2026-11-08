package com.github.gubbib.backend.domain.manage.role.mapper;

import com.github.gubbib.backend.common.DTO.PageResponseDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleSaveRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleResponseDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RoleManageMapper {
    long countRoleList(@Param("SEARCH_TYPE") String searchType, @Param("KEYWORD") String keyword, @Param("USE_YN") String useYn);
    List<RoleResponseDTO> selectRoleList(@Param("SEARCH_TYPE") String searchType, @Param("KEYWORD") String keyword, @Param("USE_YN") String useYn, @Param("PER_PAGE") int perPage, @Param("OFFSET") long offset);
    PageResponseDTO<RoleResponseDTO> selectRoleList(@Param("USER_DETAILS") CustomUserDetails userDetails, @Param("PAGE") int page, @Param("PER_PAGE") int perPage, @Param("SEARCH_TYPE") String searchType, @Param("KEYWORD") String keyword, @Param("USE_YN") String useYn);
    void insertRole(@Param("REQUEST_DTO") RoleSaveRequestDTO requestDTO, @Param("USER_DETAILS") CustomUserDetails userDetails);
    Optional<RoleResponseDTO> selectRoleByRoleCode(@Param("ROLE_CODE") String roleCode);
    void updateRole(@Param("REQUEST_DTO") RoleSaveRequestDTO requestDTO, @Param("ROLE_CODE") String roleCode, @Param("USER_DETAILS") CustomUserDetails userDetails);
    void deleteRole(@Param("ROLE_CODE") String roleCode, @Param("USER_DETAILS") CustomUserDetails userDetails);
}