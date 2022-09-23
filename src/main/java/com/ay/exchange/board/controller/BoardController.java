package com.ay.exchange.board.controller;

import com.ay.exchange.board.dto.request.WriteRequest;
import com.ay.exchange.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("board")
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
}
