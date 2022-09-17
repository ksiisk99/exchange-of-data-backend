package com.ay.exchange.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignUpRequest {
    private String userId;
    private String password;
    private String nickName;
    private String email;
}
