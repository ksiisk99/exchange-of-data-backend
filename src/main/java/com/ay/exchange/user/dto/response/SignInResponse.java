package com.ay.exchange.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInResponse {
    private String token;
}
