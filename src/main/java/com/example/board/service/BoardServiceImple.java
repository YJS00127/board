package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.dto.RequestBoardDTO;
import com.example.board.dto.ResponseBoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService{

    private final BoardMapper boardMapper;

//    @Override
//    public List<ResponseBoardDTO> findBoardAll(int page, int pageSize) {
//        int offset = (page - 1) * pageSize;
//        return boardMapper.findBoardAll(pageSize, offset);
//    }
//
//    @Override
//    public List<ResponseBoardDTO> findBoardByTitle(String keyword, int page, int pageSize) {
//       int offset = (page - 1) * pageSize;
//        return boardMapper.findBoardByTitle(keyword, pageSize, offset);
//    }

    @Override
    public ResponseBoardDTO findBoardById(Long id){
        return boardMapper.findBoardById(id);
    }

    @Override
    public int insertBoard(RequestBoardDTO board){
        return boardMapper.insertBoard(board);
    }

    @Override
    public int updateBoard(ResponseBoardDTO board) {
        return boardMapper.updateBoard(board);
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
    public int countBoards(){
        return boardMapper.countBoards();
    }

    @Override
    public int countBoardsByKeyword(String keyword){
        return boardMapper.countBoardsByKeyword(keyword);
    }


}
