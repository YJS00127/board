package com.example.board.mapper;

import com.example.board.dto.RequestBoardDTO;
import com.example.board.dto.ResponseBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<ResponseBoardDTO> findBoardAll(@Param("limit") int limit, @Param("offset") int offset);
    List<ResponseBoardDTO> findBoardByTitle(@Param("keyword") String keyword,
                                           @Param("limit") int limit,
                                           @Param("offset") int offset);
    String findBoardByIdForPw(Long id);
    int countBoards();
    int countBoardsByKeyword(String keyword);

    ResponseBoardDTO findBoardById(Long id);
    int insertBoard(RequestBoardDTO board);
    int updateBoard(ResponseBoardDTO board);
    int deleteBoard(@Param("id") Long id,@Param("pw") String pw);
}
