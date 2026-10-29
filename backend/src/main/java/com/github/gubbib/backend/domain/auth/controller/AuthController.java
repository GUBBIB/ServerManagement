package com.github.gubbib.backend.domain.auth.controller;

import com.github.gubbib.backend.common.DTO.ResultResponseDTO;
import com.github.gubbib.backend.domain.auth.DTO.SignupRequestDTO;
import com.github.gubbib.backend.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
}
