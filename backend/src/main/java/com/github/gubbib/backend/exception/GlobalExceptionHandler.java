package com.github.gubbib.backend.exception;

import com.github.gubbib.backend.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            BusinessException ex,
            HttpServletRequest request
    ) {
        logger.error(
                """
                [+] GlobalExceptionHandler - handleGlobalException
                    Request URI : {}
                    Error Code  : {}
                    Message     : {}
                    StackTrace  :
                """,
                request.getRequestURI(),
                ex.getErrorCode().name(),
                ex.getMessage(),
                ex
        );

        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse body = ErrorResponse.of(
                errorCode, request.getRequestURI()
        );

        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        logger.error(
                """
                [+] GlobalExceptionHandler - handleMethodArgumentNotValidException
                    Request URI : {}
                    Message     : {}
                    StackTrace  :
                """,
                request.getRequestURI(),
                ErrorCode.INVALID_INPUT_VALUE.getMessage(),
                ex
        );

        ErrorResponse body = ErrorResponse.of(
                ErrorCode.INVALID_INPUT_VALUE, request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request
    ) {
        logger.error(
                """
                [+] GlobalExceptionHandler - handleHttpRequestMethodNotSupportedException
                    Request URI : {}
                    HTTP Method : {}
                    Message     : {}
                    StackTrace  :
                """,
                request.getRequestURI(),
                request.getMethod(),
                ex.getMessage(),
                ex
        );

        ErrorResponse body = ErrorResponse.of(
                ErrorCode.METHOD_NOT_ALLOWED, request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(body);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {
        logger.error(
                """
                [+] GlobalExceptionHandler - handleAccessDeniedException
                    Request URI : {}
                    Message     : {}
                    StackTrace  :
                """,
                request.getRequestURI(),
                ex.getMessage(),
                ex
        );

        ErrorResponse body = ErrorResponse.of(
                ErrorCode.ACCESS_DENIED, request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex,
            HttpServletRequest request
    ) {
        logger.error(
                """
                [+] GlobalExceptionHandler - handleException
                    Request URI : {}
                    Exception   : {}
                    Message     : {}
                    StackTrace  :
                """,
                request.getRequestURI(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                ex
        );

        ErrorResponse body = ErrorResponse.of(
                ErrorCode.INTERNAL_SERVER_ERROR, request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}