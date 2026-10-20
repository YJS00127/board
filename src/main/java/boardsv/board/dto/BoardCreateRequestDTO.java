package boardsv.board.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardCreateRequestDTO {
    private Long id;

    @NotBlank(message = "※ 제목을 입력해주세요")
    private String title;
    @NotBlank(message = "※ 작성자를 입력해주세요")
    private String writer;
    @NotBlank(message = "※ 내용을 입력해주세요")
    private String content;
    @NotBlank(message = "※ 비밀번호를 입력해주세요")
    private String pw;
    private String pwConfirm;
    private LocalDateTime createdAt;
    private List<MultipartFile> files = new ArrayList<>();

    @AssertTrue(message = "※ 비밀번호가 일치하지 않습니다")
    public boolean getPwEquals(){
        return pw.equals(pwConfirm);
    }
}
