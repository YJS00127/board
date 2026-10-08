package com.example.board.mapper;

import com.example.board.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardListResponseDTO> findBoardsAll(@Param("listDTO") BoardListRequestDTO listDTO);
    String findBoardPw(@Param("id") Long id);
    int countBoards(@Param("keyword") String keyword);

    BoardDetailResponseDTO findBoardById(@Param("id") Long id);

    int insertBoard(@Param("createBoard") BoardCreateRequestDTO createBoard);
    int updateBoard(@Param("id") Long id, @Param("updateBoard") BoardUpdateRequestDTO updateBoard);
    int deleteBoard(@Param("id") Long id,@Param("pw") String pw);
}
