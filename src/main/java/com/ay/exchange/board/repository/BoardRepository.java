package com.ay.exchange.board.repository;

import com.ay.exchange.board.entity.Board;
import com.ay.exchange.board.entity.vo.MediumCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board,Long> {

    Page<Board> findByApproval(boolean approval, PageRequest pageRequest);

    Page<Board> findByApprovalAndBoardCategoryMediumCategory(boolean apporval, MediumCategory mediumCategory, PageRequest pageRequest);
}
