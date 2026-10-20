package boardsv.file.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class FileRequestDTO {
    private Long id;
    private Long boardId;
    private String originalName;
    private String savedName;
    private Long size;
    private Boolean dbYn;

    @Builder
    public FileRequestDTO(String originalName, String savedName, Long size){
        this.originalName = originalName;
        this.savedName = savedName;
        this.size = size;
    }

}
