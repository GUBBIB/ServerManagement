package com.github.gubbib.backend.domain.manage.user.mapper;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupRejectRequestDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserManageMapper {
    boolean existsByEmail(@Param("EMAIL") String email);
    LoginUserDTO selectActiveAndPendingUserByEmail(@Param("EMAIL") String email);
    void insertSignupRequest(@Param("REQUEST_DTO") SignupRequestDTO requestDTO, @Param("ENCODED_PASSWORD") String encodedPassword);
    void approveSignup(Long userId, Long approverId);
    void rejectSignup(Long userId, Long reviewerId, String rejectionReason);
    LoginUserDTO selectUserByEmail(@Param("EMAIL") String email);
    void updateSignupApprove(@Param("REQUEST_DTO") SignupApproveRequestDTO requestDTO, @Param("USER_DETAILS") CustomUserDetails userDetails);
    void updateSignupReject(@Param("REQUEST_DTO") SignupRejectRequestDTO requestDTO, @Param("USER_DETAILS") CustomUserDetails userDetails);
}
