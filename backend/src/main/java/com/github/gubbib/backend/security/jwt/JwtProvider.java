package com.github.gubbib.backend.security.jwt;

import com.github.gubbib.backend.exception.BusinessException;
import com.github.gubbib.backend.exception.ErrorCode;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public String generateAccessToken(HashMap<String, Object> parameters) {

        String email = parameters.get("EMAIL").toString();
        String role = parameters.get("ROLE").toString();

        logger.debug("[+] JwtProvider - generateAccessToken :: Access Token 생성 시작 - email={}", email);

        Instant now = Instant.now();

        Instant expiration = now.plusMillis(
                jwtProperties.accessTokenExpiration()
        );

        String accessToken = Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();

        logger.debug("[+] JwtProvider - generateAccessToken :: Access Token 생성 완료 - email={}, expiration={}", email, expiration);

        return accessToken;
    }

    public long getAccessTokenExpiration() {
        return jwtProperties.accessTokenExpiration();
    }

    public boolean validateToken(String token) {
        logger.debug("[+] JwtProvider - validateToken :: JWT 검증 시작");

        try {
            getClaims(token);

            logger.debug("[+] JwtProvider - validateToken :: JWT 검증 성공");
            return true;
        } catch (ExpiredJwtException e) {
            logger.debug("[+] JwtProvider - validateToken :: 만료된 JWT입니다.", e);
            throw new BusinessException(ErrorCode.TOKEN_EXPIRED, e);

        } catch (SecurityException e) {
            logger.debug("[+] JwtProvider - validateToken :: JWT 서명이 유효하지 않습니다.", e);
            throw new BusinessException(ErrorCode.TOKEN_INVALID_SIGNATURE, e);

        } catch (MalformedJwtException e) {
            logger.debug("[+] JwtProvider - validateToken :: JWT 형식이 올바르지 않습니다.", e);
            throw new BusinessException(ErrorCode.TOKEN_MALFORMED, e);

        } catch (UnsupportedJwtException e) {
            logger.debug("[+] JwtProvider - validateToken :: 지원하지 않는 JWT입니다.", e);
            throw new BusinessException(ErrorCode.TOKEN_UNSUPPORTED, e);

        } catch (IllegalArgumentException e) {
            logger.debug("[+] JwtProvider - validateToken :: JWT가 비어 있습니다.", e);
            throw new BusinessException(ErrorCode.TOKEN_INVALID, e);

        }
    }

    public String getEmail(String token) {
        logger.debug("[+] JwtProvider - getEmail :: JWT에서 이메일 추출 시작");

        Claims claims = getClaims(token);

        String email = claims.getSubject();

        logger.debug("[+] JwtProvider - getEmail :: JWT에서 이메일 추출 완료 - email={}", email);

        return email;
    }

    private Claims getClaims(String token) {
        return Jwts.parser()           // JWT 해석기를 만든다.
                .verifyWith(secretKey)          // 이 키로 검증하도록 설정한다.
                .build()                        // 해석기 완성
                .parseSignedClaims(token)       // 토큰을 해석 + 서명 검증 + 만료 확인
                .getPayload();                  // 검증된 Payload(Claims)를 가져온다.
    }

}
