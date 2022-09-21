package com.ay.exchange.board.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class BoardContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_content_id")
    private Long Id;

    @OneToMany(mappedBy = "boardContent"
            , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DesiredBoard>desiredBoards;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Column(nullable = false)
    private String filePath;
}
