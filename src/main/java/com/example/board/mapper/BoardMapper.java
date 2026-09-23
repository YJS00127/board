package com.example.board.mapper;

import com.example.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> findBoardAll(int limit, int offset);
    List<BoardDTO> findBoardByTitle(@Param("keyword") String keyword,
                                    @Param("limit") int limit,
                                    @Param("offset") int offset);
    int countBoards();
    int countBoardsByKeyword(@Param("keyword") String keyword);

    BoardDTO findBoardById(Long id);
    int insertBoard(BoardDTO board);
    int updateBoard(BoardDTO board);
    int deleteBoard(Long id, String pw);


}
