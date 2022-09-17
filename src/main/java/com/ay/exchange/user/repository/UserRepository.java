package com.ay.exchange.user.repository;

import com.ay.exchange.user.dto.query.UserInfoDto;
import com.ay.exchange.user.dto.response.SignInResponse;
import com.ay.exchange.user.dto.response.SignUpResponse;
import com.ay.exchange.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<UserInfoDto> findByUserIdAndPassword(String userId, String password);

    boolean existsByEmail(String email);

    boolean existsByNickName(String nickName);

}
