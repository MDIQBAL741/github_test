package candidateproject.details.Candidate.Repository;

import candidateproject.details.Candidate.Entity.SkillsList;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SkillsRepo extends JpaRepository<SkillsList,Integer> {
    @Query(value ="select * from skills_table where skills_id=:skillsId",nativeQuery = true)
    SkillsList getbyskill(int skillsId);
    @Modifying
    @Query(value ="delete from skills_table where skills_id=:skillsId",nativeQuery = true)
    void deletebyskill(int skillsId);
    @Query(value = "select * from skills_table where skills_id=:skillsId",nativeQuery = true)
    SkillsList getbyskillId(int skillsId);
}
