package com.ay.exchange.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryDto {
    private int largeCategory;
    private int mediumCategory;
    private int smallCategory;
    private int fileType;
    private int gradeType;
    private String subjectName;
    private String professorName;
}
