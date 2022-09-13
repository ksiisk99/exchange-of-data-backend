package com.ay.exchange.user.controller;

import com.ay.exchange.user.dto.request.SignInRequest;
import com.ay.exchange.user.dto.response.SignInResponse;
import com.ay.exchange.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ay/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(SignInRequest signInRequest){
        SignInResponse signInResponse=userService.signIn(signInRequest);
        return ResponseEntity.ok(signInResponse);
    }
}
