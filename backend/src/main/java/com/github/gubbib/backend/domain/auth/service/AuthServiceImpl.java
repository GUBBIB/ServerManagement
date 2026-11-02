package com.github.gubbib.backend.domain.auth.service;

import com.github.gubbib.backend.domain.auth.DTO.*;
import com.github.gubbib.backend.domain.auth.mapper.AuthMapper;
import com.github.gubbib.backend.exception.BusinessException;
import com.github.gubbib.backend.exception.ErrorCode;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import com.github.gubbib.backend.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.cfg.MapperBuilder;

@Service
@Slf4j
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 일반 Mapper
    private final AuthMapper authMapper;
    // Batch Mapper
    private final SqlSessionTemplate batchSqlSessionTemplate;
    private final MapperBuilder mapperBuilder;

    public AuthServiceImpl(
            PasswordEncoder passwordEncoder,
            JwtProvider jwtProvider,

            AuthMapper authMapper,
            SqlSessionTemplate batchSqlSessionTemplate,
            MapperBuilder mapperBuilder) {
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;

        this.authMapper = authMapper;
        this.batchSqlSessionTemplate = batchSqlSessionTemplate;
        this.mapperBuilder = mapperBuilder;
    }

    /**
     * [ 비밀번호 암호화 메소드 ]
     *
     * @param password
     * @return
     */
    private String encodingPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * [ 활성화 | 대기 유저 조회 By email ]
     *
     * @param email
     * @return
     */
    @Override
    public LoginUserDTO selectActiveAndPendingUserByEmail(String email) {
        return authMapper.selectActiveAndPendingUserByEmail(email);
    }

    /**
     * [ 회원가입 신청 ]
     *
     * @param requestDTO 회원가입 신청 정보(email, password, name, phone)
     */
    @Override
    @Transactional
    public void signupRequest(SignupRequestDTO requestDTO) {

        if(authMapper.existsByEmail(requestDTO.email())){
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = encodingPassword(requestDTO.password());

        authMapper.insertSignupRequest(requestDTO, encodedPassword);
    }

    /**
     * [ 로그인 ]
     *
     * @param requestDTO email, password
     * @return
     */
    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {

        LoginUserDTO user = authMapper.selectUserByEmail(requestDTO.email());

        if (user == null || !passwordEncoder.matches(requestDTO.password(), user.password())) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }

        String message = switch (user.status()){
            case "PENDING"      -> "회원가입 승인 대기 중입니다.";
            case "REJECTED"     -> "회원가입이 반려되었습니다.";
            case "ACTIVE"       -> "로그인되었습니다.";
            case "INACTIVE"     -> "비활성화된 계정입니다.";
            case "SUSPENDED"    -> "이용이 정지된 계정입니다.";
            case "WITHDRAWN"    -> "탈퇴한 계정입니다.";

            default             -> throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        };

        if(!"ACTIVE".equals(user.status())){
            return new LoginResponseDTO(user.status(), message, null, null);
        }
        // TODO JWT 발급 코드 추가
        String accessToken = jwtProvider.generateAccessToken(user);

        return new LoginResponseDTO(user.status(), message, accessToken, jwtProvider.getAccessTokenExpiration());
    }

    /**
     * [ 회원가입 상태 조회 ]
     *
     * @param userDetails
     * @return
     */
    @Override
    public SignupStatusResponseDTO selectSignupStatus(CustomUserDetails userDetails) {
        return authMapper.selectSignupStatus(userDetails);
    }
}
