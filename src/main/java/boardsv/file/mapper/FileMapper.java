package boardsv.file.mapper;

import boardsv.file.dto.FileDetailResponseDTO;
import boardsv.file.dto.FileRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {

    void insertAll(@Param("files") List<FileRequestDTO> files);

    List<FileDetailResponseDTO> findFileByBoardId(@Param("boardId") Long boardId);
}
