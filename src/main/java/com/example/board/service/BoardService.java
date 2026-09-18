package com.example.board.service;
import com.example.board.dto.BoardDTO;
import java.util.List;

public interface BoardService {

    List<BoardDTO> findBoardAll();
    List<BoardDTO> findBoardByTitle(String keyword);
    BoardDTO findBoardById(Long id);
    int insertBoard(BoardDTO board);
    int updateBoard(BoardDTO board);
    boolean deleteBoard(Long id, String pw);
    boolean chkPw(Long id, String pw);


}