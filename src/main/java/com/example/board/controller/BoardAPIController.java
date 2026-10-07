package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardAPIController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<Page<BoardListResponseDTO>> boardList(BoardListRequestDTO listDTO,
                                                          @PageableDefault(value=10) Pageable page){
        return ResponseEntity.ok(boardService.findBoardsAll(listDTO, page));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardDetailResponseDTO> boardDetail(@PathVariable Long id){
        BoardDetailResponseDTO board = boardService.findBoardById(id);
        if(board == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(board);
    }

    @PostMapping
    public ResponseEntity<Integer> boardInsert(@RequestBody BoardCreateRequestDTO requestDTO){
        return ResponseEntity.ok(boardService.insertBoard(requestDTO));
    }

    // 비밀번호 확인
    @PostMapping("/pw-chk/{id}")
    public ResponseEntity<Boolean> pwChk(@PathVariable Long id,
                                         @RequestBody BoardCreateRequestDTO requestDTO){
        String pw = requestDTO.getPw();
        return ResponseEntity.ok(boardService.chkPw(id, pw));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> boardUpdate(@PathVariable Long id,
                                               @RequestBody BoardUpdateRequestDTO requestDTO){
        return ResponseEntity.ok(boardService.updateBoard(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> boardDelete(@PathVariable Long id,
                                                @RequestBody BoardCreateRequestDTO requestDTO){
        String pw = requestDTO.getPw();
        return ResponseEntity.ok(boardService.deleteBoard(id, pw));
    }

}
