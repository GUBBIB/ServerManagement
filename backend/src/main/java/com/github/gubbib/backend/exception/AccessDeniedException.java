package com.github.gubbib.backend.exception;

public class AccessDeniedException extends BusinessException {

    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED);
    }
    public AccessDeniedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
