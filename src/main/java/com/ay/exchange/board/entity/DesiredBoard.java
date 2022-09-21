package com.ay.exchange.board.entity;

import com.ay.exchange.board.entity.vo.BoardCategory;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class DesiredBoard{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desired_board_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_content_id")
    private BoardContent boardContent;

    @Embedded
    private BoardCategory category;
}
