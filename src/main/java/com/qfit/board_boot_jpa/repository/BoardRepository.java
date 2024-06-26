package com.qfit.board_boot_jpa.repository;

import com.qfit.board_boot_jpa.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
