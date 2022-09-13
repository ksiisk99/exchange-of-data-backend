package com.ay.exchange.user.repository;

import com.ay.exchange.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
