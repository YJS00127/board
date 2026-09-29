package com.example.board.controller;

import com.example.board.dto.RequestBoardDTO;
import com.example.board.dto.ResponseBoardDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardAPIController {
    private final BoardService boardService;

//    @GetMapping
//    public List<ResponseEntity<BoardDTO>> boardList(Pageable page){
//        return ResponseEntity.ok(boardService.findBoardAll());
//    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseBoardDTO> boardDetail(@PathVariable Long id){
        return ResponseEntity.ok(boardService.findBoardById(id));
    }

    @PostMapping
    public ResponseEntity<Integer> boardInsert(@RequestBody RequestBoardDTO board){
        return ResponseEntity.ok(boardService.insertBoard(board));
    }

    // 비밀번호 확인
    @PostMapping("pw-chk/{id}")
    public ResponseEntity<Boolean> pwChk(@PathVariable Long id,
                                         @RequestBody String pw){
        return ResponseEntity.ok(boardService.chkPw(id, pw));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> boardUpdate(@PathVariable Long id){
        return ResponseEntity.ok(boardService.updateBoard(boardService.findBoardById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> boardDelete(@PathVariable Long id,
                                                @RequestBody String pw){
        return ResponseEntity.ok(boardService.deleteBoard(id, pw));
    }

}
