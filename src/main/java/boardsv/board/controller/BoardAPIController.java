package boardsv.board.controller;

import boardsv.board.dto.*;
import boardsv.board.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardAPIController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<Page<BoardListResponseDTO>> boardList(@ModelAttribute BoardListRequestDTO listDTO,
                                                                @PageableDefault(value=10) Pageable page){
        return ResponseEntity.ok(boardService.findBoardsAll(listDTO, page));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardDetailResponseDTO> boardDetail(@PathVariable Long id,
                                                              HttpServletRequest request,
                                                              HttpServletResponse response){
        // 게시글 존재여부 확인
        BoardDetailResponseDTO boardDetailResponse = boardService.findBoardById(id);

        if(boardDetailResponse == null){
            return ResponseEntity.notFound().build();
        }

        // 조회수 및 쿠키 처리
        boardService.addViewCount(request, response, id);

        // 조회수 증가한 response
        boardDetailResponse = boardService.findBoardById(id);

        return ResponseEntity.ok(boardDetailResponse);
    }

    @PostMapping
    public ResponseEntity<Integer> boardInsert(BoardCreateRequestDTO createBoardDTO,
                                               @RequestPart("files") List<MultipartFile> files){
        return ResponseEntity.ok(boardService.insertBoard(createBoardDTO, files));
    }

    // 비밀번호 확인
    @PostMapping("/pw-chk/{id}")
    public ResponseEntity<Boolean> pwChk(@PathVariable Long id,
                                         @RequestBody String pw){
        return ResponseEntity.ok(boardService.chkPw(id, pw));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> boardUpdate(@PathVariable Long id,
                                               @Valid @RequestBody BoardUpdateRequestDTO updateBoardDTO){
        return ResponseEntity.ok(boardService.updateBoard(id, updateBoardDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> boardDelete(@PathVariable Long id,
                                                @RequestBody String pw){
        return ResponseEntity.ok(boardService.deleteBoard(id, pw));
    }

}
