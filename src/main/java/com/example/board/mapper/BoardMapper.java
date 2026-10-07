package com.example.board.mapper;

import com.example.board.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    List<Map<String, Object>> findBoardsAll(@Param("paramMap") Map<String, Object> paramMap,@Param("keyword") String keyword);
    String findBoardByIdForPw(Long id);
    int countBoards(String keyword);

    BoardDetailResponseDTO findBoardById(@Param("id") Long id);

    int insertBoard(@Param("createdBoard") BoardCreateRequestDTO createBoard);
    int updateBoard(@Param("id") Long id, @Param("updateBoard") BoardUpdateRequestDTO updateBoard);
    int deleteBoard(@Param("id") Long id,@Param("pw") String pw);
}
