package com.example.board.service;
import com.example.board.dto.*;

import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    // 페이징을 포함한 게시글 목록
    Page<BoardListResponseDTO> findBoardsAll(BoardListRequestDTO boardListDTO, Pageable page);

    // 상세 게시글
    BoardDetailResponseDTO findBoardById(Long id);

    // 게시글 등록
    int insertBoard(BoardCreateRequestDTO createBoard);

    //게시글 수정
    int updateBoard(Long id, BoardUpdateRequestDTO updateBoard);

    // 게시글 삭제
    int deleteBoard(Long id, String pw);

    // 게시글 수정, 삭제를 위한 비밀번호 확인
    boolean chkPw(Long id, String pw);
}