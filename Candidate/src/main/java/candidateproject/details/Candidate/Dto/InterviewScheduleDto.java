package candidateproject.details.Candidate.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewScheduleDto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id")
        private int scheduleId;
        @NotNull(message = "select program")
        private String program;
        private String candidateName;
        @NotNull(message = "select candidate email")
        private String candiEmail;
        @NotNull(message = "select panel email")
        private String panelName;
        private String panelEmail;

        private LocalTime startTime;
        private LocalTime endTime;
        @NotNull(message = "select level")
        private String level;
        private String skill;
        @NotNull(message = "choose date")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate date;
        private int numberOfSlots;
    }

