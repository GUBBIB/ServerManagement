package com.github.gubbib.backend.domain.user.mapper;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.user.DTO.PasswordChangeRequestDTO;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<LoginUserDTO> selectUserByUserId(@Param("USER_ID") Long userId);
    Optional<LoginUserDTO> selectUserByEmail(@Param("EMAIL") String email);
    LoginUserDTO selectActiveAndPendingUserByEmail(@Param("EMAIL") String email);
    void changePassword(@Param("USER_DETAILS") CustomUserDetails userDetails, @Param("ENCODED_PASSWORD") String encodedPassword);
}
