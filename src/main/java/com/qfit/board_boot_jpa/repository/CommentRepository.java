package com.qfit.board_boot_jpa.repository;

import com.qfit.board_boot_jpa.entity.BoardEntity;
import com.qfit.board_boot_jpa.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
