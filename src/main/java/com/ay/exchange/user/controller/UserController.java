package com.ay.exchange.user.controller;

import com.ay.exchange.user.dto.request.SignInRequest;
import com.ay.exchange.user.dto.request.SignUpRequest;
import com.ay.exchange.user.dto.request.VerificationCodeRequest;
import com.ay.exchange.user.dto.response.SignInResponse;
import com.ay.exchange.user.dto.response.SignUpResponse;
import com.ay.exchange.user.dto.response.VerificationCodeResponse;
import com.ay.exchange.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ay/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(userService.signIn(signInRequest));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

    @GetMapping("/verification-code/{email}")
    public ResponseEntity<VerificationCodeResponse> getVerificationCode(
            @PathVariable("email") String email
    ){
        VerificationCodeResponse response=userService
                .getVerificationCode(email);
        return ResponseEntity.ok(response);
    }


}
