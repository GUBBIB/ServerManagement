package com.github.gubbib.backend.domain.manage.user.controller;

import com.github.gubbib.backend.common.DTO.ResultResponseDTO;
import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupRejectRequestDTO;
import com.github.gubbib.backend.domain.manage.user.service.UserManageService;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/manage/users")
/**
 * ADMIN 이거나 MANAGER와 USER_MANAGER 역할을 모두 보유 해야 한다.
 */
@PreAuthorize("""
        hasRole('ADMIN') or
        (hasRole('MANAGER') and hasRole('USER_MANAGER'))
        """)
public class UserManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserManageService userManageService;

    @PostMapping("/signup/approve")

    public ResponseEntity<ResultResponseDTO> signupApprove(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid SignupApproveRequestDTO requestDTO
    ) {
        LoginUserDTO user = userManageService.signupApprove(requestDTO);

        String message = String.format("회원가입 요청 완료\n 이메일: %s\n이름: %s", user.email(), user.name());

        return ResponseEntity.ok(
                new ResultResponseDTO(message)
        );
    }

    @PostMapping("/signup/reject")
    public ResponseEntity<ResultResponseDTO> signupReject(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid SignupRejectRequestDTO requestDTO
    ) {
        LoginUserDTO user = userManageService.signupReject(requestDTO);

        String message = String.format("회원가입 요청 반려\n 이메일: %s\n이름: %s\n 사유: %s\n", user.email(), user.name(), requestDTO.reason());

        return ResponseEntity.ok(
                new ResultResponseDTO(message)
        );
    }
}