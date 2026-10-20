package boardsv.common.file;

import boardsv.file.dto.FileRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {
    private final String uploadPath = Paths.get(System.getProperty("user.dir"), "uploads/").toString();

    public List<FileRequestDTO> uploadFiles(final List<MultipartFile> multipartFiles){
        List<FileRequestDTO> files = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if(multipartFile.isEmpty()){
                continue;
            }
            files.add(uploadFile(multipartFile));
        }
        return files;
    }

    public FileRequestDTO uploadFile(final MultipartFile multipartFile){
        if(multipartFile.isEmpty()){
            return null;
        }

        String savedName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String uploadPath = getUploadPath(today) + File.separator + savedName;
        File uploadFile = new File(uploadPath);

        try{
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileRequestDTO.builder()
                .originalName(multipartFile.getOriginalFilename())
                .savedName(savedName)
                .size(multipartFile.getSize())
                .build();
    }

    public String generateSaveFilename(final String filename){
        String uuid = UUID.randomUUID().toString();
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

    public String getUploadPath(){
        return makeDirectories(uploadPath);
    }

    private String getUploadPath(final String addPath){
        return makeDirectories(uploadPath + File.separator + addPath);
    }

    private String makeDirectories(final String path){
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir.getPath();
    }
}
