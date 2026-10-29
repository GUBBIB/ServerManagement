package com.github.gubbib.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {



    // ===== Common =====
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_001", "서버 내부 오류가 발생했습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "COMMON_002", "잘못된 요청입니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON_003", "요청 값이 유효하지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_004", "지원하지 않는 HTTP 메서드입니다."),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_005", "요청한 리소스를 찾을 수 없습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "COMMON_006", "접근 권한이 없습니다."),

    // ===== JWT =====
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "JWT_001", "만료된 토큰입니다."),
    TOKEN_INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED, "JWT_002", "토큰 서명이 유효하지 않습니다."),
    TOKEN_MALFORMED(HttpStatus.UNAUTHORIZED, "JWT_003", "토큰 형식이 올바르지 않습니다."),
    TOKEN_UNSUPPORTED(HttpStatus.UNAUTHORIZED, "JWT_004", "지원하지 않는 토큰입니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "JWT_005", "유효하지 않은 토큰입니다."),
    TOKEN_MISSING(HttpStatus.UNAUTHORIZED, "JWT_006", "토큰이 존재하지 않습니다."),

    // ===== AUTH ====
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "AUTH_001", "중복된 이메일입니다."),

    // ===== USER =====
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER_001", "이미 존재하는 이메일입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_002", "회원을 찾을 수 없습니다."),



    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
