package candidateproject.details.Candidate.Repository;

import candidateproject.details.Candidate.Entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepo extends JpaRepository<Requirement,Integer> {
}
