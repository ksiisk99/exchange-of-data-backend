package com.ay.exchange.user.entity;

import com.ay.exchange.common.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nickName;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Temporal(TemporalType.DATE)
    private Date suspendedDate;
    protected User() {}
}
