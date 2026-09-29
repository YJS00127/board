package com.example.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("board")
@Controller
public class BoardViewController {

    @GetMapping
    public String boardList(){
        return "list";
    }

    @GetMapping("/insert")
    public String insertBoard() {
        return "insert";
    }

    @GetMapping("/detail/{id}")
    public String boardDetail(){
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String boardUpdate(){
        return "update";
    }
}
