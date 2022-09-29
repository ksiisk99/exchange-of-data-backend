package com.ay.exchange.board.dto.query;

import com.ay.exchange.board.entity.vo.BoardCategory;

public interface BoardInfoDto {
    Long getId();
    String getTitle();
    String getWriter();
    String getViews();
    BoardCategory getBoardCategory(); //게시글 조회기 때문에 필요 없을 듯 나중에 지울 예정
    Integer getNumberOfFilePages();
    Integer getNumberOfSuccessFulExchanges();
    String getCreatedDate();
}
