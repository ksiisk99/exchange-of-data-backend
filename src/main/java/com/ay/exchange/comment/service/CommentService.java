package com.ay.exchange.comment.service;

import com.ay.exchange.board.dto.request.DeleteRequest;
import com.ay.exchange.comment.dto.request.WriteRequest;
import com.ay.exchange.comment.entity.Comment;
import com.ay.exchange.comment.repository.CommentRepository;
import com.ay.exchange.jwt.JwtTokenProvider;
import com.ay.exchange.user.exception.InvalidUserRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtTokenProvider jwtTokenProvider;

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

    public void deleteComment(DeleteRequest deleteRequest, String token) {
        if(jwtTokenProvider.getEmail(token).equals(token)){
            commentRepository.deleteById(deleteRequest.getCommentId());
        }else{
            throw new InvalidUserRoleException();
        }
    }
}
