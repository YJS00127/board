package com.example.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.board.dto.BoardDTO;
import com.example.board.service.BoardService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // View 이동
    @GetMapping
    private String boardList(Model model,
                             @RequestParam(required = false) String keyword){
        List<BoardDTO> boardList;
        if(keyword == null || keyword.isBlank()){
            boardList = boardService.findBoardAll();
        } else{
            boardList = boardService.findBoardByTitle(keyword);
        }
        boardList.sort(Comparator.comparing(BoardDTO::getId).reversed());
        model.addAttribute("boardList", boardList);

        return "list";
    }

    @GetMapping("/detail/{id}")
    private String boardDetail(@PathVariable Long id, Model model){
        model.addAttribute("boardDetail", boardService.findBoardById(id));
        return "detail";
    }

    @GetMapping("/insert")
    private String boardInsertForm(){
        return "insert";
    }

    @GetMapping("/update/{id}")
    private String boardUpdate(@PathVariable Long id, Model model){
        model.addAttribute("boardDetail", boardService.findBoardById(id));
        return "update";
    }

    // 로직 처리
    @PostMapping
    private String boardInsert(@ModelAttribute BoardDTO board){
        boardService.insertBoard(board);
        return "redirect:/board";
    }

    // 수정 전에 비밀번호 확인
    @PostMapping("/{id}/check-pw")
    @ResponseBody
    private boolean check(@PathVariable Long id, @RequestParam String pw){
        return boardService.chkPw(id, pw);
    }

    @PutMapping("/{id}")
    private String boardUpdate(@PathVariable Long id, BoardDTO board){
        boardService.updateBoard(board);
        return "redirect:/board/detail/" + id;
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    private boolean boardDelete(@PathVariable Long id, @RequestParam String pw) {
        if(boardService.chkPw(id, pw)){
            return boardService.deleteBoard(id, pw);
        } else {
            return false;
        }
    }


}
