package com.example.board.service;

import com.example.board.dto.*;
import com.example.board.mapper.BoardMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public Page<BoardListResponseDTO> findBoardsAll(BoardListRequestDTO listDTO, Pageable page) {
        listDTO.setOffset(page.getOffset());
        listDTO.setPageSize(page.getPageSize());

        List<BoardListResponseDTO> contents = boardMapper.findBoardsAll(listDTO);
        int totalElements = boardMapper.countBoards(listDTO.getKeyword(), listDTO.getCategory());
        return new PageImpl<>(contents, page, totalElements);
    }

    @Override
    public BoardDetailResponseDTO findBoardById(Long id) {
        return boardMapper.findBoardById(id);
    }

    // 조회수 증가 및 쿠키 처리
    @Override
    public void addViewCount(HttpServletRequest request, HttpServletResponse response, Long id) {
        // 기존에 생성된 쿠키를 저장할 객체
        Cookie oldCookie = null;

        // 요청에서 온 쿠키들을 쿠키 객체 리스트에 저장
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LookedUpBoard")) {
                    oldCookie = cookie;
                    break;
                }
            }
        }
        if(oldCookie != null){
            if(!oldCookie.getValue().contains("[" + id.toString() + "]")){
                boardMapper.viewCountPlus(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 5);
                response.addCookie(oldCookie);
            }
        } else{
            boardMapper.viewCountPlus(id);

            Cookie newCookie = new Cookie("LookedUpBoard", "[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 5);
            response.addCookie(newCookie);
        }
    }

    @Override
    public int insertBoard(BoardCreateRequestDTO createBoard) {
        return boardMapper.insertBoard(createBoard);
    }

    @Override
    public int updateBoard(Long id, BoardUpdateRequestDTO updateBoard) {
        return boardMapper.updateBoard(id, updateBoard);
    }

    @Override
    public int deleteBoard(Long id, String pw) {
        return boardMapper.deleteBoard(id, pw);
    }

    @Override
    public boolean chkPw(Long id, String pw) {
        return (boardMapper.findBoardPw(id).equals(pw));
    }
}
