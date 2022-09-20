package com.ay.exchange.user.controller;

import com.ay.exchange.user.dto.request.SignInRequest;
import com.ay.exchange.user.dto.request.SignUpRequest;
import com.ay.exchange.user.dto.response.SignInResponse;
import com.ay.exchange.user.dto.response.SignUpResponse;
import com.ay.exchange.user.dto.response.VerificationCodeResponse;
import com.ay.exchange.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(
            @RequestBody SignInRequest signInRequest
    ) {
        return ResponseEntity.ok(userService.signIn(signInRequest));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(
            @RequestBody SignUpRequest signUpRequest
    ) {
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

    @GetMapping({"/sign-up/verification-code/{email}"
            , "/find-password/verification-code/{email}"})
    public ResponseEntity<VerificationCodeResponse> getVerificationCode(
            @PathVariable("email") String email
    ) {
        return ResponseEntity.ok(userService.getVerificationCode(email));
    }

    @GetMapping("/temporary-password/{number}")
    public ResponseEntity<String> getTemporaryPassword(
            @PathVariable("number") String number
            , @RequestHeader("token") String verificationCodeToken
    ) {
        return ResponseEntity.ok(userService
                .getTemporaryPassword(number, verificationCodeToken));
    }

    @GetMapping("/existence-id/{userId}")
    public ResponseEntity<Boolean> existsUserId(
            @PathVariable("userId") String userId
    ) {
        return ResponseEntity.ok(userService.checkExistsUserId(userId));
    }

    @GetMapping("/existence-email/{email}")
    public ResponseEntity<Boolean> existsEmail(
            @PathVariable("email") String email
    ) {
        return ResponseEntity.ok(userService.checkExistsNickName(email));
    }

    @GetMapping("/find-id/{email}")
    public ResponseEntity<String> findUserId(
            @PathVariable("email") String email
    ) {
        return ResponseEntity.ok(userService.findUserId(email));
    }


//    @PatchMapping("/update-password")
//    public ResponseEntity<Boolean> updatePassword(
//            @RequestBody UpdatePasswordRequest updatePasswordRequest
//    ) {
//        return ResponseEntity.ok(
//                userService.updateUserPassword(updatePasswordRequest)
//        );
//    }

}

