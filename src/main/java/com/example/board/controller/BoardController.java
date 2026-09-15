package com.example.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.example.board.dto.BoardDTO;
import com.example.board.service.BoardService;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    private String boardList(Model model,
                             @RequestParam(required = false) String keyword){
        List<BoardDTO> boardList;
        if(keyword == null || keyword.isBlank()){
            boardList = boardService.findBoardAll();
        } else{
            boardList = boardService.findBoardByTitle(keyword);
        }
        model.addAttribute("boardList", boardList);
        model.addAttribute("keyword", keyword);
        
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

    @PostMapping
    private String boardInsert(@ModelAttribute BoardDTO board){
        boardService.insertBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{id}")
    private String boardUpdate(@PathVariable Long id, Model model){
        model.addAttribute("boardDetail", boardService.findBoardById(id));
        return "update";
    }

    @PutMapping("/{id}")
    private String boardUpdate(@PathVariable Long id, BoardDTO board){
        boardService.updateBoard(board);
        return "redirect:/board/detail/" + id;
    }

    @GetMapping("/delete/{id}")
    private String boardDelete(@PathVariable Long id, Model model){
        model.addAttribute("boardDetail", boardService.findBoardById(id));
        return "delete";
    }

    @DeleteMapping("/{id}")
    private String boardDelete(@PathVariable Long id, String pw, @ModelAttribute BoardDTO board){
        boardService.deleteBoard(id, pw);
        return "redirect:/board/list";
    }

}
