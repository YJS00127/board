package boardsv.file.service;

import boardsv.file.dto.FileDetailResponseDTO;
import boardsv.file.dto.FileRequestDTO;
import boardsv.file.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final FileMapper fileMapper;

    @Override
    @Transactional
    public void insertAll(Long boardId, List<FileRequestDTO> files) {
        if(CollectionUtils.isEmpty(files)){
            return;
        }
        for (FileRequestDTO file : files){
            file.setBoardId(boardId);
        }
        fileMapper.insertAll(files);
    }

    @Override
    public List<FileDetailResponseDTO> findFileByBoardId(Long boardId){
        return fileMapper.findFileByBoardId(boardId);
    }
}
