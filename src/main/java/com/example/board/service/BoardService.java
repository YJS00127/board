package com.example.board.service;
import com.example.board.dto.BoardDTO;
import java.util.List;

public interface BoardService {

    List<BoardDTO> findBoardAll();
    BoardDTO findBoardById(Long id);
    int insertBoard(BoardDTO board);
    int updateBoard(BoardDTO board);
    int deleteBoard(Long id, String pw);

}
