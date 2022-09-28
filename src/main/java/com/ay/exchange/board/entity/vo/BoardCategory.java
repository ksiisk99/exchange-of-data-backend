package com.ay.exchange.board.entity.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCategory {

    @Builder
    public BoardCategory(LargeCategory largeCategory, MediumCategory mediumCategory, SmallCategory smallCategory, FileType fileType, GradeType gradeType, String subjectName, String professorName) {
        this.largeCategory = largeCategory;
        this.mediumCategory = mediumCategory;
        this.smallCategory = smallCategory;
        this.fileType = fileType;
        this.gradeType = gradeType;
        this.subjectName = subjectName;
        this.professorName = professorName;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LargeCategory largeCategory;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MediumCategory mediumCategory;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SmallCategory smallCategory;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Enumerated(EnumType.STRING)
    private GradeType gradeType;

    private String subjectName;
    private String professorName;
}
