package com.ay.exchange.comment.service;

import com.ay.exchange.board.entity.BoardContent;
import com.ay.exchange.board.exception.NotFoundBoardException;
import com.ay.exchange.board.repository.BoardContentRepository;
import com.ay.exchange.comment.dto.request.DeleteRequest;
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
    private final BoardContentRepository boardContentRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void writeComment(WriteRequest writeRequest) {
        BoardContent boardContent = boardContentRepository
                .findById(writeRequest.getBoardContentId()) //fk니깐 조회하지 않아도 db단에서 처리가 가능하지 않을까?
                .orElseThrow(
                        () -> {
                            throw new NotFoundBoardException();
                        }
                );

        Comment comment = Comment.builder()
                .boardContent(boardContent)
                .writer(writeRequest.getWriter())
                .content(writeRequest.getContent())
                .depth(writeRequest.getDepth())
                .groupId(writeRequest.getGroupId())
                .email(writeRequest.getEmail())
                .build();

        commentRepository.save(comment);
    }

    public void deleteComment(DeleteRequest deleteRequest, String token) {

        if (isAuthorized(token, deleteRequest.getEmail())) { //추후 @PreAuthorize로 해결하자
            if (deleteRequest.getDepth()) //자식 댓글
                commentRepository.deleteById(deleteRequest.getCommentId());
            else //부모 댓글
                commentRepository.deleteAllByGroupId(deleteRequest.getGroupId());
        } else {
            throw new InvalidUserRoleException();
        }
    }

    private boolean isAuthorized(String token, String email) {
        return jwtTokenProvider.getEmail(token).equals(email);
    }
}
