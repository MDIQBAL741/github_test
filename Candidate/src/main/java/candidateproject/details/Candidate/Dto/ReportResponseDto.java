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
    private LocalDate date1;
    private LocalDate date2;
    private List<String> candidateEmail;
    private List<String> panelEmail;
    private List<String> status;
    private List<String> comment;
    private List<String> level;

}
