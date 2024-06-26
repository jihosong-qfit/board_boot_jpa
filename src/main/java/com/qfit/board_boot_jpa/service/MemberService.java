package com.qfit.board_boot_jpa.service;

import com.qfit.board_boot_jpa.dto.MemberDTO;
import com.qfit.board_boot_jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {

    }
}
