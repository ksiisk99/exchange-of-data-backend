package com.ay.exchange.comment.service;

import com.ay.exchange.comment.dto.request.WriteRequest;
import com.ay.exchange.comment.entity.Comment;
import com.ay.exchange.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void writeComment(WriteRequest writeRequest) {
        Comment comment=Comment.builder()
                .boardContentId(writeRequest.getBoardContentId())
                .writer(writeRequest.getWriter())
                .content(writeRequest.getContent())
                .depth(writeRequest.getDepth())
                .group(writeRequest.getGroup())
                .build();

        commentRepository.save(comment);
    }
}
