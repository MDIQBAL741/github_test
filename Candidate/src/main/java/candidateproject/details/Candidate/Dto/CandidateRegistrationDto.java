package candidateproject.details.Candidate.Dto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.CandidateStatus;
import candidateproject.details.Candidate.Entity.InterviewSchedule;
import candidateproject.details.Candidate.Entity.Requirement;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRegistrationDto {
    private int candidateId;
    private String candidateType;
    private String employeeID;
    @NotNull(message = "user name not be null")
    private String name;
    @NotNull(message = "email can't be null")
    @Email(message = "invalid email")
    private String email;
    @NotNull(message = "skills can't be null")
    private String skill;
    private int level;
    @NotNull(message = "phone can't ne null")
    @Pattern(regexp = "^[0-9]{10}$", message = "invalid phone no")
    private String phone;
    @NotNull(message = "required experiance")
    private String experience;
    private String baselocation;
    private String preferedloaction;
    private String currentctc;
    private String expectedctc;
    private String noticePeriod;
    private String anyOffer;
    private String currentOrg;
//    @NotNull(message = "pancard can't be null")
//    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "invalid Pan")
    private String pancard;
    @OneToOne
    @JoinColumn(name = "reqId",referencedColumnName = "reqId")
    private Requirement requirement;
    @Column
    private String status;

    private MultipartFile resume;

}
