package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class BoardDetailResponseDTO {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private String createdAt;

    private Long limit;
    private Long offset;
}
