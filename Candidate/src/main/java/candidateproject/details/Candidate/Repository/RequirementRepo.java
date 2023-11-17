package candidateproject.details.Candidate.Repository;

import candidateproject.details.Candidate.Entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepo extends JpaRepository<Requirement,Integer> {
    @Query(value = "select * from requirement where project_name=:projectName",nativeQuery = true)
    Requirement findbyProjectName(String projectName);
}
