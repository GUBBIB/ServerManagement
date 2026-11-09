package com.github.gubbib.backend.domain.user.controller;

import com.github.gubbib.backend.common.DTO.ResultResponseDTO;
import com.github.gubbib.backend.domain.user.DTO.MyInfoResponseDTO;
import com.github.gubbib.backend.domain.user.DTO.PasswordChangeRequestDTO;
import com.github.gubbib.backend.domain.user.service.UserService;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    /**
     * [ 내 정보 조회 ]
     *
     * @param userDetails
     * @return
     */
    @GetMapping("/me")
    public ResponseEntity<ResultResponseDTO> getMyInfo(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        MyInfoResponseDTO userInfo = userService.getMyInfo(userDetails);

        return ResponseEntity.ok(
                new ResultResponseDTO("내 정보 조회", userInfo)
        );
    }

    @PatchMapping("/me/password")
    public ResponseEntity<ResultResponseDTO> changePassword(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid PasswordChangeRequestDTO requestDTO
    ) {
        userService.changePassword(userDetails, requestDTO);

        return ResponseEntity.ok(
                new ResultResponseDTO("비밀번호 변경")
        );
    }
}
