package boardsv.file.mapper;

import boardsv.file.dto.FileCreateRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper {
    void findFiles(@Param("files") FileCreateRequestDTO files,
                   @Param("boardId") Long boardId);

    void insertFiles(@Param("files") FileCreateRequestDTO files);
}
