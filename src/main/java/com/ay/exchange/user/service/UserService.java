package com.ay.exchange.user.service;

import com.ay.exchange.jwt.JwtTokenProvider;
import com.ay.exchange.user.dto.query.UserInfoDto;
import com.ay.exchange.user.dto.request.SignInRequest;
import com.ay.exchange.user.dto.request.SignUpRequest;
import com.ay.exchange.user.dto.response.SignInResponse;
import com.ay.exchange.user.dto.response.SignUpResponse;
import com.ay.exchange.user.dto.response.VerificationCodeResponse;
import com.ay.exchange.user.entity.Authority;
import com.ay.exchange.user.entity.User;
import com.ay.exchange.user.exception.NotExistsUserException;
import com.ay.exchange.user.exception.NotExistsUserIdException;
import com.ay.exchange.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    public SignInResponse signIn(SignInRequest signInRequest) {
        UserInfoDto userInfoDto = userRepository
                .findUserInfoByUserId(signInRequest.getUserId())
                .orElseThrow(() -> {
                    throw new NotExistsUserException();
                });

        if (passwordEncoder.matches(signInRequest.getPassword(),
                userInfoDto.getPassword())) {
            return new SignInResponse(
                    "ABCD"
                    , userInfoDto.getNickName()
                    , userInfoDto.getAuthority()
                    , userInfoDto.getSuspendedDate()
            );
        }
        return null;
    }

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        String pass = passwordEncoder.encode(signUpRequest.getPassword());

        userRepository.save(new User(
                signUpRequest.getUserId()
                , pass
                , signUpRequest.getEmail()
                , signUpRequest.getNickName()
                , Authority.User
        ));

        return new SignUpResponse(
                "ABCDEF"
                , signUpRequest.getNickName()
                , Authority.User
        );
    }

    public VerificationCodeResponse getVerificationCode(String email) {
        String verificationCode = createVerificationCode();

        sendVerificationCodeByMail(email, verificationCode);

        return new VerificationCodeResponse(
                jwtTokenProvider.createVerificationCodeToken(verificationCode));
    }

    private void sendVerificationCodeByMail(String email, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("자료주냥 인증번호");
        message.setText(verificationCode);

        javaMailSender.send(message);
    }

    private String createVerificationCode() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            sb.append((int) (Math.random() * 9 + 1));
        }

        return sb.toString();
    }

    public Boolean checkExistsUserId(String userId) {
        return userRepository.existsById(userId);
    }

    public Boolean checkExistsNickName(String nickName) {
        return userRepository.existsById(nickName);
    }

    public String findUserId(String email) {
        String userId=userRepository
                .findUserIdByEmail(email)
                .orElseThrow(()-> {
                    throw new NotExistsUserIdException();
                })
                .getUserId();

        return userId;
    }
}
