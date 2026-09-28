package com.example.board.controller;

import com.example.board.dto.BoardDTO;
import com.example.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // View 이동
    @GetMapping
    private String boardList(Model model,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(defaultValue = "1") int page){
        int pageSize = 10;

        int total = boardService.countBoards();
        int totalPages = (int)Math.ceil((double)total / pageSize);

        if(page < 1) page = 1;
        if(page > totalPages) page = totalPages;

        List<BoardDTO> boardList;
        if(keyword == null || keyword.isBlank()){
            boardList = boardService.findBoardAll(page, pageSize);
        } else{
            total = boardService.countBoardsByKeyword(keyword);
            totalPages = (int)Math.ceil((double)total / pageSize);

            if(page < 1) page = 1;
            if(page > totalPages) page = totalPages;

            boardList = boardService.findBoardByTitle(keyword, page, pageSize);
        }
        model.addAttribute("boardList", boardList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

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
    @PostMapping("/{id}/pw-chk")
    @ResponseBody
    private boolean pwChk(@PathVariable Long id, @RequestParam String pw){
        return boardService.chkPw(id, pw);
    }

    @PostMapping
    private String boardInsert(@ModelAttribute BoardDTO board){
        boardService.insertBoard(board);
        return "redirect:/board";
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
