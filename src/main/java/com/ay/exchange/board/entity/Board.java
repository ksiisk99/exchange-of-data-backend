package com.ay.exchange.board.entity;

import com.ay.exchange.board.entity.vo.BoardCategory;
import com.ay.exchange.board.entity.vo.LargeCategory;
import com.ay.exchange.board.entity.vo.MediumCategory;
import com.ay.exchange.board.entity.vo.SmallCategory;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
@Getter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long Id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Embedded
    private BoardCategory category;

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

    @OneToOne(fetch = FetchType.LAZY
            , cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "board_content_id")
    private BoardContent boardContent;


}