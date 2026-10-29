package com.github.gubbib.backend.domain.auth.service;

import com.github.gubbib.backend.domain.auth.DTO.AuthUserDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;
import com.github.gubbib.backend.domain.auth.mapper.AuthMapper;
import com.github.gubbib.backend.exception.BusinessException;
import com.github.gubbib.backend.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

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

        String encodingPassword = encodingPassword(requestDTO.password());


    }

    /**
     * [ 활성화 | 대기 유저 조회 By email ]
     *
     * @param email
     * @return
     */
    @Override
    public AuthUserDTO selectActiveAndPendingUserByEmail(String email) {
        return authMapper.selectActiveAndPendingUserByEmail(email);
    }
}
