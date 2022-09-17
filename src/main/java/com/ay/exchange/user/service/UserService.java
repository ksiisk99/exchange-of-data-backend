package com.ay.exchange.user.service;

import com.ay.exchange.jwt.JwtTokenProvider;
import com.ay.exchange.user.dto.query.UserInfoDto;
import com.ay.exchange.user.dto.request.SignInRequest;
import com.ay.exchange.user.dto.request.SignUpRequest;
import com.ay.exchange.user.dto.request.VerificationCodeRequest;
import com.ay.exchange.user.dto.response.SignInResponse;
import com.ay.exchange.user.dto.response.SignUpResponse;
import com.ay.exchange.user.dto.response.VerificationCodeResponse;
import com.ay.exchange.user.entity.Authority;
import com.ay.exchange.user.entity.User;
import com.ay.exchange.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;

    public SignInResponse signIn(SignInRequest signInRequest) {
        String pass=bCryptPasswordEncoder.encode(signInRequest.getPassword());
        System.out.println(pass);

        UserInfoDto userInfoDto = userRepository.findByUserIdAndPassword(
                signInRequest.getUserId()
                , pass
        ).get();

        return new SignInResponse(
                "ABCD"
                , userInfoDto.getNickName()
                , userInfoDto.getAuthority()
                , userInfoDto.getSuspendedDate()
        );
    }

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        String pass=bCryptPasswordEncoder.encode(signUpRequest.getPassword());
        System.out.println(pass);

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

    public VerificationCodeResponse getVerificationCode(VerificationCodeRequest verificationCodeRequest) {
        String verificationCode=createVerificationCode();

        sendVerificationCodeByMail(verificationCodeRequest.getEmail(), verificationCode);

        return new VerificationCodeResponse(
                jwtTokenProvider.createVerificationCodeToken(verificationCode));
    }

    private void sendVerificationCodeByMail(String email, String verificationCode){
        SimpleMailMessage message=new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("자료주냥 인증번호");
        message.setText(verificationCode);

        javaMailSender.send(message);
    }

    private String createVerificationCode(){
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<6;i++){
            sb.append(String.valueOf((int)(Math.random()*9+1)));
        }

        return sb.toString();
    }

}
