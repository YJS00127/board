package com.example.board.service;

import com.example.board.dto.*;
import com.example.board.mapper.BoardMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public Page<BoardListResponseDTO> findBoardsAll(BoardListRequestDTO listDTO, Pageable page) {
        listDTO.setOffset(page.getOffset());
        listDTO.setPageSize(page.getPageSize());

        List<BoardListResponseDTO> contents = boardMapper.findBoardsAll(listDTO);
        int totalElements = boardMapper.countBoards(listDTO.getKeyword());
        return new PageImpl<>(contents, page, totalElements);
    }

    @Override
    public BoardDetailResponseDTO findBoardById(Long id){
        return boardMapper.findBoardById(id);
    }

    @Override
    public int insertBoard(BoardCreateRequestDTO createBoard){
        return boardMapper.insertBoard(createBoard);
    }

    @Override
    public int updateBoard(Long id, BoardUpdateRequestDTO updateBoard) {
        return boardMapper.updateBoard(id, updateBoard);
    }

    @Override
    public int deleteBoard(Long id, String pw){
        return boardMapper.deleteBoard(id, pw);
    }

    @Override
    public boolean chkPw(Long id, String pw){
        return (boardMapper.findBoardPw(id).equals(pw));
    }
}
