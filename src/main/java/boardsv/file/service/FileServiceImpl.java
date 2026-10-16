package boardsv.file.service;

import boardsv.file.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final FileMapper fileMapper;

    @Override
    public String saveFile(MultipartFile file){
        try {
//            int pos = file.getOriginalFilename().lastIndexOf(".");
//            String extension = file.getOriginalFilename().substring(pos + 1);
            String safeFileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString();
//                    + "." + extension;

            Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads/");
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(safeFileName);

            file.transferTo(filePath.toFile());

            return safeFileName;
        } catch (IOException e){
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }
}
