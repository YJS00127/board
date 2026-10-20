package boardsv.file.service;

import boardsv.file.dto.FileRequestDTO;
import boardsv.file.dto.FileDetailResponseDTO;

import java.util.List;

public interface FileService {
    void insertAll(Long boardId, List<FileRequestDTO> files);

}
