package com.qfit.board_boot_jpa.entity;

import jakarta.persistence.*;

//boot 3부터 jakarta 패키지 이름이 바뀜
@Entity
@Table(name = "board_table")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String boardWriter;

    @Column
    private String boardTitle;

}
