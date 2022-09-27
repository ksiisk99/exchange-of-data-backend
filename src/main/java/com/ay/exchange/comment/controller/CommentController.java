package com.ay.exchange.comment.controller;

import com.ay.exchange.comment.dto.request.DeleteRequest;
import com.ay.exchange.comment.dto.request.WriteRequest;
import com.ay.exchange.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/write")
    public ResponseEntity<Boolean>writeComment(
            @RequestBody WriteRequest writeRequest
    ){
        commentService.writeComment(writeRequest);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean>deleteComment(
            @RequestBody DeleteRequest deleteRequest,
            @RequestHeader("token") String token
    ){
        commentService.deleteComment(deleteRequest, token);
        return ResponseEntity.ok(true);
    }
}
