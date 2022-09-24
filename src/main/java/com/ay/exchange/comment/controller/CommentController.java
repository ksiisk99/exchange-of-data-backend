package com.ay.exchange.comment.controller;

import com.ay.exchange.comment.dto.request.WriteRequest;
import com.ay.exchange.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/write")
    public ResponseEntity<Boolean>writeComment(
            @RequestBody WriteRequest writeRequest
    ){
        commentService.writeComment(writeRequest);
        return ResponseEntity.ok(true);
    }
}
