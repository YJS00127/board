package boardsv.board.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardCreateRequestDTO {
    private Long id;

    @NotBlank(message = "※ 제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "※ 작성자를 입력해주세요.")
    private String writer;
    @NotBlank(message = "※ 내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "※ 비밀번호를 입력해주세요.")
    private String pw;
    private String pwConfirm;
    private LocalDateTime createdAt;

    @AssertTrue(message = "※ 비밀번호를 다시 확인해주세요.")
    public boolean getPwEquals(){
        return pw.equals(pwConfirm);
    }
}
