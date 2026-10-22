package boardsv.board.dto;

import boardsv.file.dto.FileDetailResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
public class BoardDetailResponseDTO {
    private String title;
    private String writer;
    private String content;
    private Long viewCount;
    private String createdAt;
    @Setter
    private List<FileDetailResponseDTO> files;
}
