package com.github.gubbib.backend.domain.manage.role.service;

import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.SignupRejectRequestDTO;
import com.github.gubbib.backend.domain.manage.role.DTO.UserStatusUpdateRequestDTO;
import com.github.gubbib.backend.domain.manage.role.mapper.RoleManageMapper;
import com.github.gubbib.backend.domain.user.service.UserService;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
/**
 * ADMIN 이거나 MANAGER와 USER_MANAGER 역할을 모두 보유 해야 한다.
 */
@PreAuthorize("""
        hasRole('ADMIN') or
        (hasRole('MANAGER') and hasRole('ROLE_MANAGER'))
        """)
public class RoleManageServiceImpl implements RoleManageService {


}
