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
    public Page<Map<String, Object>> findBoardsAll(Map<String, Object> paramMap,
                                                   Pageable page) {
        paramMap.put("offset", page.getOffset());
        paramMap.put("pageSize", page.getPageSize());

        String keyword = (String) paramMap.get("keyword");
        List<Map<String, Object>> contents = boardMapper.findBoardsAll(paramMap, keyword);

        int count = boardMapper.countBoards(keyword);

        return new PageImpl<>(contents, page, count);
    }

    @Override
    public BoardDetailResponseDTO findBoardById(Long id){
        return boardMapper.findBoardById(id);
    }

    @Override
    public int insertBoard(BoardCreateRequestDTO board){
        return boardMapper.insertBoard(board);
    }

    @Override
    public int updateBoard(Long id, BoardUpdateRequestDTO updateRequest) {
        return boardMapper.updateBoard(id, updateRequest);
    }

    @Override
    public int deleteBoard(Long id, String pw){
        return boardMapper.deleteBoard(id, pw);
    }

    @Override
    public boolean chkPw(Long id, String pw){
        return (boardMapper.findBoardByIdForPw(id).equals(pw));
    }

    @Override
    public int countBoards(String keyword){
        return boardMapper.countBoards(keyword);
    }


}
