package com.github.gubbib.backend.domain.manage.user.service;

import com.github.gubbib.backend.common.DTO.PageResponseDTO;
import com.github.gubbib.backend.domain.auth.DTO.LoginUserDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupApproveRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupRejectRequestDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.SignupUserListDTO;
import com.github.gubbib.backend.domain.manage.user.DTO.UserStatusUpdateRequestDTO;
import com.github.gubbib.backend.domain.manage.user.mapper.UserManageMapper;
import com.github.gubbib.backend.domain.user.service.UserService;
import com.github.gubbib.backend.exception.BusinessException;
import com.github.gubbib.backend.exception.ErrorCode;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
/**
 * ADMIN 이거나 MANAGER와 USER_MANAGER 역할을 모두 보유 해야 한다.
 */
@PreAuthorize("""
        hasRole('ADMIN') or
        (hasRole('MANAGER') and hasRole('USER_MANAGER'))
        """)
public class UserManageServiceImpl implements UserManageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    // 일반 Mapper
    private final UserManageMapper userManageMapper;
    // Batch Mapper
    private final SqlSessionTemplate batchSqlSessionTemplate;

    public UserManageServiceImpl(
            UserService userService,
            UserManageMapper userManageMapper,
            SqlSessionTemplate batchSqlSessionTemplate
    ) {
        this.userService = userService;
        this.userManageMapper = userManageMapper;
        this.batchSqlSessionTemplate = batchSqlSessionTemplate;
    }

    /**
     * [ 회원가입 요청 목록 조회 ]
     *
     * @param userDetails
     * @param page          : 조회 페이지
     * @param perPage       : 조회 개수
     * @param searchType    : 검색 타입 [ 전체 | 이름 | 이메일 | 휴대폰 번호 ]
     * @param keyword       : 검색어
     * @return
     */
    @Override
    public PageResponseDTO<SignupUserListDTO> selectSignupUserList(CustomUserDetails userDetails, int page, int perPage, String searchType, String keyword) {
        if (page < 1 || perPage < 1) {
            throw new BusinessException(ErrorCode.INVALID_PAGINATION);
        }

        long offset = ((long) page - 1) * perPage;

        long totalCount = userManageMapper.countSignupUserList(searchType, keyword);

        List<SignupUserListDTO> list = userManageMapper.selectSignupUserList(searchType, keyword, perPage, offset);

        return new PageResponseDTO<>(totalCount, page, perPage, list);
    }

    /**
     * [ 회원가입 요청 승인 ]
     *
     * @param userDetails
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional
    public LoginUserDTO signupApprove(CustomUserDetails userDetails, SignupApproveRequestDTO requestDTO) {
        LoginUserDTO user = userService.selectUserByUserId(requestDTO.user_id());

        userManageMapper.updateSignupApprove(requestDTO, userDetails);

        return user;
    }

    /**
     * [ 회원가입 요청 반려 ]
     *
     * @param userDetails
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional
    public LoginUserDTO signupReject(CustomUserDetails userDetails, SignupRejectRequestDTO requestDTO) {
        LoginUserDTO user = userService.selectUserByUserId(requestDTO.user_id());

        userManageMapper.updateSignupReject(requestDTO, userDetails);

        return user;
    }

    /**
     * [ 유저 상태 변경 ]
     * @param userDetails
     * @param userId
     * @param requestDTO
     * @return
     */
    @Override
    public void updateUserStatus(CustomUserDetails userDetails, Long userId, UserStatusUpdateRequestDTO requestDTO) {
        LoginUserDTO user = userService.selectUserByUserId(userId);

        userManageMapper.updateUserStatus(requestDTO, userDetails, userId);
    }
}
