package com.ay.exchange.board.dto.response;

import com.ay.exchange.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@Getter
public class BoardResponse {
    private int totalPages;
    private List<Board> boardList;
}
