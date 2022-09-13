package com.ay.exchange.user.service;

import com.ay.exchange.jwt.JwtTokenProvider;
import com.ay.exchange.user.dto.request.SignInRequest;
import com.ay.exchange.user.dto.response.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtTokenProvider jwtTokenProvider;

    public SignInResponse signIn(SignInRequest signInRequest) {
        return new SignInResponse("ABC");
    }


}
