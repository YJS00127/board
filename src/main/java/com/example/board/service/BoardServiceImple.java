package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> findBoardAll() {
        return boardMapper.findBoardAll();
    }

    @Override
    public List<BoardDTO> findBoardByTitle(String keyword) { return boardMapper.findBoardByTitle(keyword);}

    @Override
    public BoardDTO findBoardById(Long id){
        return boardMapper.findBoardById(id);
    }

    @Override
    public int insertBoard(BoardDTO board){
        return boardMapper.insertBoard(board);
    }

    @Override
    public int updateBoard(BoardDTO board){
        return boardMapper.updateBoard(board);
    }

    @Override
    public boolean deleteBoard(Long id, String pw){
        return boardMapper.deleteBoard(id, pw);
    }
}
