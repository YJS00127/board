package com.example.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateRequestDTO {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "작성자를 입력해주세요.")
    private String writer;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
