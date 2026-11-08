package com.github.gubbib.backend.domain.manage.user.mapper;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupRejectRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupUserListDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.UserStatusUpdateRequestDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserManageMapper {
    void updateSignupApprove(@Param("REQUEST_DTO") SignupApproveRequestDTO requestDTO, @Param("USER_DETAILS") CustomUserDetails userDetails);
    void updateSignupReject(@Param("REQUEST_DTO") SignupRejectRequestDTO requestDTO, @Param("USER_DETAILS") CustomUserDetails userDetails);
    void updateUserStatus(@Param("REQUEST_DTO") UserStatusUpdateRequestDTO requestDTO, @Param("USER_DETAILS") CustomUserDetails userDetails, @Param("USER_ID") Long userId);
    long countSignupUserList(@Param("SEARCH_TYPE") String searchType, @Param("KEYWORD") String keyword);
    List<SignupUserListDTO> selectSignupUserList(@Param("SEARCH_TYPE") String searchType, @Param("KEYWORD") String keyword, @Param("PER_PAGE") int perPage, @Param("OFFSET") long offset);
}