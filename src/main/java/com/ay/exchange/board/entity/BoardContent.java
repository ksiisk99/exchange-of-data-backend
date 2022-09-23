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

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false)
    private String filePath;

    public BoardContent(String content, String filePath) {
        this.content = content;
        this.filePath = filePath;
    }

}
