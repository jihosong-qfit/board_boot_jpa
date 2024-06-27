package com.qfit.board_boot_jpa.repository;

import com.qfit.board_boot_jpa.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}
