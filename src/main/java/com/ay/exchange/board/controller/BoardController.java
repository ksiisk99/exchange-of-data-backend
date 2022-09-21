package com.ay.exchange.board.controller;

import com.ay.exchange.board.dto.request.WriteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("board")
public class BoardController {

//    @PostMapping("/write")
//    public ResponseEntity<Boolean> writeBoard(
//            @RequestBody WriteRequest writeRequest
//    ) {
//
//        return true;
//    }
}
