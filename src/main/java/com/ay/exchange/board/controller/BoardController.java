package com.ay.exchange.board.controller;

import com.ay.exchange.board.dto.request.WriteRequest;
import com.ay.exchange.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Boolean>getBoardList(){
        //boardService.getBoardList();

        return ResponseEntity.ok(true);
    }
}
