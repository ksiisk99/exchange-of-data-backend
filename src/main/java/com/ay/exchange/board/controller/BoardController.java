package com.ay.exchange.board.controller;

import com.ay.exchange.board.dto.request.DeleteRequest;
import com.ay.exchange.board.dto.request.WriteRequest;
import com.ay.exchange.board.entity.Board;
import com.ay.exchange.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity<Boolean> writeBoard(
            @RequestBody WriteRequest writeRequest
    ) {
        boardService.writeBoard(writeRequest);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<List<Board>>getBoardList(
            @RequestParam("page") int page
    ){
        return ResponseEntity.ok( boardService.getBoardList(page));
    }
}
