package candidateproject.details.Candidate.Dto;
import candidateproject.details.Candidate.Entity.InterviewSchedule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PanelDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int panelId;
    @NotNull(message = "Panel Name shouldn't be Null")
    private String panelName;
    private String skill;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @Column(unique = true)
    private String panelEmail;
    @NotNull
    private int numberOfSlots;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    private String level;

}
