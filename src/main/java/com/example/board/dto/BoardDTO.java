package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDTO {
    private Long id;
    private Long rowNum;
    private String title;
    private String content;
    private String writer;
    private String pw;
    private LocalDateTime createdAt;


}
