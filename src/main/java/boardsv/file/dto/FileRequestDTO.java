package boardsv.file.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class FileRequestDTO {
    private Long id;
    @Setter
    private Long boardId;
    private String originalName;
    private String savedName;
    private Long size;

    @Builder
    public FileRequestDTO(String originalName, String savedName, Long size){
        this.originalName = originalName;
        this.savedName = savedName;
        this.size = size;
    }

}
