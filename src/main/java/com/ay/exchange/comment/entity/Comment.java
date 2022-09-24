package com.ay.exchange.comment.entity;

import com.ay.exchange.board.entity.BoardContent;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY) //board에서 조회만 하는거면 1:N 단방향이 더 낫지않을까?
    @JoinColumn(name = "board_content_id")
    private BoardContent boardContent;

    @Column(nullable = false)
    private Long boardContentId;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean depth;

    @Column(nullable = false)
    private Long group;
}
