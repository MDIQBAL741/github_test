package candidateproject.details.Candidate.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="skillsTable")
public class SkillsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillsId;
    @NotNull
    private String skill;


}
