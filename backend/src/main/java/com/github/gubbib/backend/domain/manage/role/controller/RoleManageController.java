package com.github.gubbib.backend.domain.manage.role.controller;


import com.github.gubbib.backend.common.DTO.ResultResponseDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleCreateRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.RoleListResponseDTO;
import com.github.gubbib.backend.domain.manage.role.service.RoleManageService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/manage/roles")
/**
 * ADMIN 이거나 MANAGER와 USER_MANAGER 역할을 모두 보유 해야 한다.
 */
@PreAuthorize("""
        hasRole('ADMIN') or
        (hasRole('MANAGER') and hasRole('ROLE_MANAGER'))
        """)
public class RoleManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoleManageService roleManageService;

    /**
     * [ 역할 목록 조회 ]
     *
     * @param userDetails
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<ResultResponseDTO> getRoles(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<RoleListResponseDTO> roleListResponseDTO = roleManageService.selectRoleList(userDetails);

        return ResponseEntity.ok(
                new ResultResponseDTO("역할 목록 조회", roleListResponseDTO)
        );
    }

    @PostMapping("/")
    public ResponseEntity<ResultResponseDTO> createRole(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid RoleCreateRequestDTO requestDTO
    ) {
        roleManageService.insertRole(requestDTO, userDetails);

        return ResponseEntity.ok(
                new ResultResponseDTO("역할 추가")
        );
    }

}