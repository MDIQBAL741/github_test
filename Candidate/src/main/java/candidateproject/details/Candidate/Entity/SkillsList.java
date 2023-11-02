package candidateproject.details.Candidate.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="skillsTable")
public class SkillsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillsId;
    @NotNull
    private String skill;


}
