package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> findBoardAll(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return boardMapper.findBoardAll(pageSize, offset);
    }

    @Override
    public List<BoardDTO> findBoardByTitle(String keyword, int page, int pageSize) {
       int offset = (page - 1) * pageSize;
        return boardMapper.findBoardByTitle(keyword, pageSize, offset);
    }

    @Override
    public BoardDTO findBoardById(Long id){
        return boardMapper.findBoardById(id);
    }

    @Override
    public int insertBoard(BoardDTO board){
        return boardMapper.insertBoard(board);
    }

    @Override
    public boolean updateBoard(BoardDTO board) {
        return boardMapper.updateBoard(board) > 0;
    }

    @Override
    public boolean deleteBoard(Long id, String pw){
        return boardMapper.deleteBoard(id, pw) > 0;
    }

    @Override
    public boolean chkPw(Long id, String pw){
     return (boardMapper.findBoardById(id).getPw().equals(pw));
    }

    @Override
    public int countBoards(){
        return boardMapper.countBoards();
    }

    @Override
    public int countBoardsByKeyword(String keyword){
        return boardMapper.countBoardsByKeyword(keyword);
    }


}
