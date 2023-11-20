package candidateproject.details.Candidate.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {
    private List<LocalDate> date;

    private List<String> candidateEmail;
    private List<String> panelEmail;
    private List<String> status;
    private List<String> comment;
    private List<String> level;
    private Long phone;
    private List<String> skill;

}
