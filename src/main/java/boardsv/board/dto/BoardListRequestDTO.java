package boardsv.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class BoardListRequestDTO {
    private String keyword;
    private String category;
    private Long offset;
    private int pageSize;
}
