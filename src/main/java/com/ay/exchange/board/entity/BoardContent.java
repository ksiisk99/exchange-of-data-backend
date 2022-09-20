package com.ay.exchange.board.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BoardContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private Board board;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Column(nullable = false)
    private String filePath;
}
