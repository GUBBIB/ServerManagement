package com.github.gubbib.backend.domain.manage.mapper;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManageMapper {
    boolean existsByEmail(@Param("EMAIL") String email);
    LoginUserDTO selectActiveAndPendingUserByEmail(@Param("EMAIL") String email);
    void insertSignupRequest(@Param("REQUEST_DTO") SignupRequestDTO requestDTO, @Param("ENCODED_PASSWORD") String encodedPassword);
    void approveSignup(Long userId, Long approverId);
    void rejectSignup(Long userId, Long reviewerId, String rejectionReason);
    LoginUserDTO selectUserByEmail(@Param("EMAIL") String email);
}
