package com.example.board.dto;

import lombok.Getter;

@Getter
public class BoardListResponseDTO {
    private Long id;
    private String title;
    private String writer;
    private String createdAt;
}
