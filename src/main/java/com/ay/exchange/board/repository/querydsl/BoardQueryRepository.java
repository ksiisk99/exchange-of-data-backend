package com.ay.exchange.board.repository.querydsl;

import com.ay.exchange.board.dto.query.BoardInfoDto;
import com.ay.exchange.board.entity.vo.MediumCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardQueryRepository {
    Page<BoardInfoDto> findBoards(boolean apporval, MediumCategory mediumCategory, Pageable pageable, List<String> departments, List<String> grades, List<String> types);
}
