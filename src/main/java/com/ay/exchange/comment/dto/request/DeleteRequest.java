package com.ay.exchange.comment.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteRequest {
    private String email;
    private Long commentId;
    private Boolean depth;
    private Long groupId;
}
