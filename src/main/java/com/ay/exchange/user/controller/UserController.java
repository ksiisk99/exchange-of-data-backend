package com.ay.exchange.user.controller;

import com.ay.exchange.user.dto.request.SignInRequest;
import com.ay.exchange.user.dto.response.SignInResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ay/user")
public class UserController {

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(SignInRequest signInRequest){
        return ResponseEntity.ok(new SignInResponse("test"));
    }
}
