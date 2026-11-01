package com.github.gubbib.backend.domain.auth.controller;

import com.github.gubbib.backend.common.DTO.ResultResponseDTO;
import com.github.gubbib.backend.domain.auth.DTO.LoginRequestDTO;
import com.github.gubbib.backend.domain.auth.DTO.LoginResponseDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;
import com.github.gubbib.backend.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ResultResponseDTO> signup(
            @RequestBody @Valid SignupRequestDTO requestDTO
    ) {
        authService.signupRequest(requestDTO);

        return ResponseEntity.ok(
                new ResultResponseDTO("회원가입 신청 성공")
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ResultResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO requestDTO
    ) {
        LoginResponseDTO responseDTO = authService.login(requestDTO);

        return ResponseEntity.ok(
                new ResultResponseDTO("계정 확인 완료", responseDTO)
        );
    }

    // TODO 로그아웃에 대해서 고민 - 프론트에서 토큰을 지우더라도 만료 전까지는 유효한 토큰이기에 탈취된 토큰으로 API 요청이 가능함.
}
