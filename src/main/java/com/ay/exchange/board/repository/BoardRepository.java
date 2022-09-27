package com.ay.exchange.board.repository;

import com.ay.exchange.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.ContentHandler;

public interface BoardRepository extends JpaRepository<Board,Long> {

    Page<Board> findByApproval(boolean approval, PageRequest pageRequest);
}
