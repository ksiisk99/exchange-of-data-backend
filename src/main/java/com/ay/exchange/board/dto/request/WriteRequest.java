package com.ay.exchange.board.dto.request;

import com.ay.exchange.board.entity.LargeCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WriteRequest {
    private String title;
    private String writer;
    private LargeCategory category;
    private String subjectName;
    private String professorName;
    private Integer numberOfFilePages;

}
