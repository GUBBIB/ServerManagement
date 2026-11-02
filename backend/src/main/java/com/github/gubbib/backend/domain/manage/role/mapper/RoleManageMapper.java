package com.github.gubbib.backend.domain.manage.role.mapper;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.SignupRejectRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.UserStatusUpdateRequestDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleManageMapper {

}