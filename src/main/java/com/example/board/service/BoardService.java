package com.example.board.service;
import com.example.board.dto.BoardDTO;
import com.example.board.dto.BoardUpdateRequestDTO;
import com.example.board.dto.ResponseBoardDTO;

public interface BoardService {
    // 페이징을 포함한 게시글 목록
//    List<BoardDTO> findBoardAll();
//    List<RequestBoardDTO> findBoardAll(int page, int pageSize);
//    List<RequestBoardDTO> findBoardByTitle(String keyword, int page, int pageSize);

    // 상세 게시글
    ResponseBoardDTO findBoardById(Long id);

    // 게시글 등록
    int insertBoard(BoardDTO board);

    //게시글 수정
    int updateBoard(Long id, BoardUpdateRequestDTO board);

    // 게시글 삭제
    int deleteBoard(Long id, String pw);

    // 게시글 수정, 삭제를 위한 비밀번호 확인
    boolean chkPw(Long id, String pw);

    // pageSize를 위한 전체 게시글 수
    int countBoards();
    int countBoardsByKeyword(String keyword);
}