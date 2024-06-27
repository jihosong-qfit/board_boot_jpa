package com.qfit.board_boot_jpa.entity;

import com.qfit.board_boot_jpa.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//DB 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(length = 20, nullable = false) //크기 20, not null
    private String boardWriter;

    @Column //default 크기 255, null
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private int fileAttached; // 첨부: 1 | 미첨부: 0

    //mappedBy는 자식에서 선언한 joinColumn명으로 맞춰주면된다.
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    //entity를 dto로 변환
    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardHits(0);
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setFileAttached(0); //파일 없음.
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        return boardEntity;
    }

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardHits(0);
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setFileAttached(1); //파일 없음.
        return boardEntity;
    }
}
