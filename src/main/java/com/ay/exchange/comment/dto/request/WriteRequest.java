package com.ay.exchange.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WriteRequest {
    private Long boardContentId;
    private String email;
    private String writer;
    private String content;
    private Boolean depth;
    private Long groupId;
}
