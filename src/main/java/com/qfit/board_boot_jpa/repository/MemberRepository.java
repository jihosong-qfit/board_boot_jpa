package com.qfit.board_boot_jpa.repository;

import com.qfit.board_boot_jpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
