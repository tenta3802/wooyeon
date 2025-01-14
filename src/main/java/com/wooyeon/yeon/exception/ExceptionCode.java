package com.wooyeon.yeon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    USER_NOT_FOUND("DB에 유저가 존재하지 않습니다", "2000"),
    USER_MATCH_NOT_FOUND("DB에 매치 정보가 존재하지 않습니다", "2001"),
    FCM_SEND_FAIL_ERROR("FCM 메시지 전송에 실패", "2002"),

    AUTHENTICATION_FAILED("인증 실패", "4000"),
    AUTHORIZATION_FAILED("접근 권한 없음", "4001"),
    LOGIN_USER_NOT_FOUND("DB에 로그인된 유저 정보가 존재하지 않습니다", "4002"),
    INVALID_JWT_TOKEN("JWT 유효성 검증 에러", "4003"),
    EXPIRED_JWT_TOKEN("JWT 만료 에러", "4004"),
    UNSUPPORTED_JWT_TOKEN("JWT 지원 하지 않음", "4005"),
    JWT_CLAIMS_STRING_IS_EMPTY("JWT 값 비어 있음", "4006"),
    INVALID_REFRESH_TOKEN("Refresh Token 값이 유효하지 않습니다", "4007"),
    NOT_EXIST_REFRESH_TOKEN("Refresh Token 값이 존재하지 않습니다", "4008"),
    NOT_MATCH_REFRESH_TOKEN("DB 내 Refresh Token 값이 일치하지 않습니다", "4009"),
    SECURITY_CONTEXT_IS_EMPTY("Security Context USER 정보가 존재하지 않습니다", "4010"),

    PROFILE_NOT_FOUND("DB에 profile이 존재하지 않습니다", "3000");

    private final String description;
    private final String resultCode;
}