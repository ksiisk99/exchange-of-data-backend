package com.ay.exchange.board.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
@Getter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LargeCategory largeCategory;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MediumCategory mediumCategory;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SmallCategory smallCategory;

    private String subjectName;
    private String professorName;

    @Column(nullable = false)
    private Long views;

    @Column(nullable = false)
    private Integer numberOfFilePages;

    @Column(nullable = false)
    private Integer numberOfSuccessfulExchanges;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private String createdDate;

    @Column(nullable = false)
    private Boolean approval;
}