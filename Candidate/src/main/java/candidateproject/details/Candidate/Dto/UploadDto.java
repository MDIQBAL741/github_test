package candidateproject.details.Candidate.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadDto {
    private String name;
    private String email;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private MultipartFile resume;


}
