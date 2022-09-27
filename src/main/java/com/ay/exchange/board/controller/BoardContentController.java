package com.ay.exchange.board.controller;

import com.ay.exchange.board.dto.request.DeleteRequest;
import com.ay.exchange.board.service.BoardContentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardContentController {
    private final BoardContentService boardContentService;

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteBoard(
            @RequestBody DeleteRequest deleteRequest,
            @RequestHeader("token") String token
    ){
        boardContentService.deleteBoard(token,deleteRequest);
        return ResponseEntity.ok(true);
    }
}
