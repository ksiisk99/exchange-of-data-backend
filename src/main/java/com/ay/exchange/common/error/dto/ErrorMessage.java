package com.ay.exchange.common.error.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    NOT_EXIST_USER(HttpStatus.NOT_FOUND,"아이디 또는 비밀번호가 존재하지 않습니다");

    private final HttpStatus status;
    private final String description;
}
