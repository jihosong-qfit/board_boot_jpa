package com.qfit.board_boot_jpa.repository;

import com.qfit.board_boot_jpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //이메일로 회원 정보 조회 (select * from member_table where memeber_email=?)
    //Optional: null이 올 수 있는 값을 감싸는 Wrapper 클래스로 참조하더라도 NPE이 발생하지 않도록 한다.
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
