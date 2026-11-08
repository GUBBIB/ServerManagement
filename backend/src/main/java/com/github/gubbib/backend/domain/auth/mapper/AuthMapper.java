package com.github.gubbib.backend.domain.auth.mapper;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupStatusResponseDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

@Mapper
public interface AuthMapper {
    boolean existsByEmail(@Param("EMAIL") String email);
    LoginUserDTO selectActiveAndPendingUserByEmail(@Param("EMAIL") String email);
    void insertSignupUser(@Param("REQUEST_DTO") SignupRequestDTO requestDTO, @Param("ENCODED_PASSWORD") String encodedPassword, @Param("GENERATED_KEY")HashMap<String, Object> generatedKey);
    SignupStatusResponseDTO selectSignupStatus(@Param("USER_DETAILS") CustomUserDetails userDetails);
    void insertSignupRole(@Param("USER_ID") Long userId);
}
