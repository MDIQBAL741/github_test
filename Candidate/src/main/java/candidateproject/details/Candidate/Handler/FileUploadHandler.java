package candidateproject.details.Candidate.Handler;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHandler {
//    public final String dir="C:\\Users\\md.quraishi\\Downloads\\Candidate\\Candidate\\src\\main\\resources\\static\\resume";
    public final String dir=new ClassPathResource("static/resume/").getFile().getAbsolutePath();


    public FileUploadHandler() throws IOException {
    }


    public boolean uploadFile(MultipartFile multipartFile) {
        boolean f = false;
        try {
            Files.copy(multipartFile.getInputStream(), Paths.get(dir + File.separator + multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
