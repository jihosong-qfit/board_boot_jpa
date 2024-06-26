package com.qfit.board_boot_jpa.service;

import com.qfit.board_boot_jpa.dto.BoardDTO;
import com.qfit.board_boot_jpa.entity.BoardEntity;
import com.qfit.board_boot_jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//DTO -> Entity: Repository로 Entity로 넘겨준다. (Entity Class)
//Entity -> DTO: Controller에서 호출 시에는 DTO로 넘겨준다. (DTO Class)
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
