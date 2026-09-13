package com.example.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
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
    private String boardList(Model model, HttpServletRequest request){
        List<BoardDTO> boardList = new ArrayList<>();
        boardList = boardService.findBoardAll();
        model.addAttribute("boardList", boardList);
        return "list";
    }

    @GetMapping("/detail/{id}")
    private String boardDetail(@PathVariable Long id, Model model){
        model.addAttribute("boardDetail", boardService.findBoardById(id));
        return "detail";
    }

    @GetMapping("/insert")
    private String boardInsertForm(@ModelAttribute BoardDTO board){
        return "insert";
    }

    @PostMapping("/insert")
    private String boardInsert(@ModelAttribute BoardDTO board, HttpServletRequest request){
        boardService.insertBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{id}")
    private String boardUpdate(@PathVariable Long id, Model model){
        model.addAttribute("boardDetail", boardService.findBoardById(id));
        return "update";
    }

    @PostMapping("/update")
    private String boardUpdate(@ModelAttribute BoardDTO board){
        boardService.updateBoard(board);
        long id = board.getId();
        String detail_id = Long.toString(id);
        return "redirect:/board/detail/"+detail_id;

    }

    @DeleteMapping("/delete/{id}")
    private String boardDelete(@PathVariable Long id, String pw, Model model){
        boardService.deleteBoard(id, pw);
        return "redirect:/board/list";
    }

}
