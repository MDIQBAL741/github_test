package candidateproject.details.Candidate.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="candidate")
public class CandidateRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidateId")
    private int candidateId;
    private String candidateType;
    private String employeeID;
    @OneToOne
    @JoinColumn(name = "reqId",referencedColumnName = "reqId")
    private Requirement requirement;
    private String name;
    @Column(unique = true)
    private String email;
    private String skill;
    private int level;
    @Column(unique = true)
    private Long phone;
    private String experience;
    private String baselocation;
    private String preferedloaction;
    private String anyOffer;
    private String currentOrg;
    private String  currentctc;
    private String  expectedctc;
    private String noticePeriod;
//    @Column(unique = true)
    private String pancard;
    @Column
    private String status;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] resume;

    public CandidateRegistration(String fileName, String name, byte[] bytes) {
    }

    public CandidateRegistration(int candidateId, String name, String fileDownloadUri, int length) {
    }



    public Object orElse(CandidateRegistration candidateRegistration) {
        return new CandidateRegistration();
    }
}

