package candidateproject.details.Candidate.Dto;

import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.Panel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDto {
    private Panel panel;
    private CandidateRegistration candidateRegistration;
    private LocalDate startDate;
    private LocalDate endDate;

}
