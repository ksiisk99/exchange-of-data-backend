package com.ay.exchange.board.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteRequest {
    private String email;
    private Long commentId;
}
