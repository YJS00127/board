package boardsv.board.dto;

import lombok.Getter;

@Getter
public class BoardDetailResponseDTO {
    private String title;
    private String writer;
    private String content;
    private Long viewCount;
    private String createdAt;
}
