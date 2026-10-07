package com.example.board.controller;

import com.example.board.dto.BoardCreateRequestDTO;
import com.example.board.dto.BoardUpdateRequestDTO;
import com.example.board.dto.BoardDetailResponseDTO;
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
    public ResponseEntity<Object> boardList(Map<String, Object> paramMap,
                                            @PageableDefault(value=10) Pageable page){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Page<Map<String, Object>> result = boardService.findBoardsAll(paramMap, page);
        int pageGroup = (int) Math.ceil(((double)result.getNumber()+1) / 10);
        resultMap.put("pages", result);
        resultMap.put("pageGroup", pageGroup);

        return ResponseEntity.ok().body(resultMap);
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
