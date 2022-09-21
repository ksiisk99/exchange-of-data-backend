package com.ay.exchange.board.entity.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class BoardCategory {
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
}
