package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
public class BoardDetailResponseDTO {
    private String title;
    private String writer;
    private String content;
    private String createdAt;
}
