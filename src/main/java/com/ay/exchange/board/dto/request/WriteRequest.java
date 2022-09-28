package com.ay.exchange.board.dto.request;

import com.ay.exchange.board.dto.CategoryDto;
import com.ay.exchange.board.entity.vo.LargeCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WriteRequest {
    private String title;
    private String writer;
    private CategoryDto categoryDto;
    private Integer numberOfFilePages;
    private String content;
}