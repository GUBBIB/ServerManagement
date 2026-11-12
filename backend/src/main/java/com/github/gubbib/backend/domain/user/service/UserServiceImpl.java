package com.github.gubbib.backend.domain.user.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.role.service.RoleService;
import com.github.gubbib.backend.domain.user.DTO.MyInfoResponseDTO;
import com.github.gubbib.backend.domain.user.DTO.PasswordChangeRequestDTO;
import com.github.gubbib.backend.domain.user.mapper.UserMapper;
import com.github.gubbib.backend.exception.BusinessException;
import com.github.gubbib.backend.exception.ErrorCode;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;
    private final SqlSessionTemplate batchSqlSessionTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserMapper userMapper,
            SqlSessionTemplate batchSqlSessionTemplate,
            PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.batchSqlSessionTemplate = batchSqlSessionTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * [ 활성화 | 대기 유저 조회 By email ]
     *
     * @param email
     * @return
     */
    @Override
    public LoginUserDTO selectActiveAndPendingUserByEmail(String email) {
        return userMapper.selectActiveAndPendingUserByEmail(email);
    }

    /**
     * [ 회원 역할 조회 - USER_ID ]
     *
     * @param userId
     * @return
     */
    @Override
    public LoginUserDTO selectUserByUserId(Long userId) {
        return userMapper.selectUserByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * [ 회원 조회 - EMAIL ]
     *
     * @param email
     * @return
     */
    @Override
    public LoginUserDTO selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * [ 내 정보 조회 ]
     *
     * @param userDetails
     * @return
     */
    @Override
    public MyInfoResponseDTO getMyInfo(CustomUserDetails userDetails) {
        LoginUserDTO user = selectUserByEmail(userDetails.getUser().email());

        return new MyInfoResponseDTO(
                user.user_id(),
                user.email(),
                user.name(),
                user.status(),
                user.phone(),
                user.roles()
        );
    }

    @Override
    @Transactional
    public void changePassword(CustomUserDetails userDetails, PasswordChangeRequestDTO requestDTO) {
        LoginUserDTO user = selectUserByEmail(userDetails.getUser().email());

        if (!passwordEncoder.matches(requestDTO.currentPassword(), user.password())) {
            throw new BusinessException(ErrorCode.INVALID_CURRENT_PASSWORD);
        }

        String encodedPassword = passwordEncoder.encode(requestDTO.newPassword());

        userMapper.changePassword(userDetails, encodedPassword);

        /**
         *  TODO 비밀번호 변경 후, 로그아웃 및 JWT Token 처리에 대해서 고민
         *  AuthController에 로그아웃과 동일한 문제이다.
        **/
    }
}
