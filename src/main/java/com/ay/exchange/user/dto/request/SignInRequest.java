package com.ay.exchange.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInRequest {
    private String userId;
    private String password;
    private String email;
    private String nickName;
}
