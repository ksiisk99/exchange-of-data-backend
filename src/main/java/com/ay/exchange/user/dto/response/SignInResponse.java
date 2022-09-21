package com.ay.exchange.user.dto.response;

import com.ay.exchange.user.entity.vo.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInResponse {
    private String accessToken;
    private String nickName;
    private Authority authority;
    private String suspendedDate;
}
