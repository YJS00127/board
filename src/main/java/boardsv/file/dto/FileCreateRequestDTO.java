package boardsv.file.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class FileCreateRequestDTO {
    private Long id;
    private Long boardId;
    private String originalName;
    private String savedName;
    private int size;

    @Builder
    public FileCreateRequestDTO(Long boardId, String originalName, String savedName, int size){
        this.boardId = boardId;
        this.originalName = originalName;
        this.savedName = savedName;
        this.size = size;
    }

}
