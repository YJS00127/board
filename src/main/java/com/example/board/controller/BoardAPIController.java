package com.example.board.controller;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.BoardUpdateRequestDTO;
import com.example.board.dto.ResponseBoardDTO;
import com.example.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardAPIController {
    private final BoardService boardService;

//    @GetMapping
//    public List<ResponseEntity<BoardDTO>> boardList(Pageable page){
//        return ResponseEntity.ok(boardService.findBoardAll());
//    }

    //
    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseBoardDTO> boardDetail(@PathVariable Long id){
        ResponseBoardDTO board = boardService.findBoardById(id);
        if(board == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(board);
    }


    //
    @PostMapping
    public ResponseEntity<Integer> boardInsert(@RequestBody BoardDTO board){
        return ResponseEntity.ok(boardService.insertBoard(board));
    }

    //
    // 비밀번호 확인
    @PostMapping("/pw-chk/{id}")
    public ResponseEntity<Boolean> pwChk(@PathVariable Long id,
                                         @RequestBody BoardDTO request){
        String pw = request.getPw();
        return ResponseEntity.ok(boardService.chkPw(id, pw));
    }

    //
    @PutMapping("/{id}")
    public ResponseEntity<Integer> boardUpdate(@PathVariable Long id,
                                               @RequestBody BoardUpdateRequestDTO updateRequest){
        return ResponseEntity.ok(boardService.updateBoard(id, updateRequest));
    }

    //
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> boardDelete(@PathVariable Long id,
                                                @RequestBody BoardDTO request){
        String pw = request.getPw();
        return ResponseEntity.ok(boardService.deleteBoard(id, pw));
    }

}
