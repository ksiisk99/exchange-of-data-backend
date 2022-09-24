package com.ay.exchange.user.entity;

import com.ay.exchange.board.entity.Board;
import com.ay.exchange.common.entity.BaseEntity;
import com.ay.exchange.user.entity.vo.Authority;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class User extends BaseEntity {
    @Id
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "board_id")
    private List<Board> boards;
    protected User() {}

    public User(String userId,String password, String email, String nickName, Authority authority) {
        this.userId=userId;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.authority = authority;
    }
}
