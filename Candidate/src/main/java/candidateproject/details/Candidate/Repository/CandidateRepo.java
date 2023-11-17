package candidateproject.details.Candidate.Repository;

import candidateproject.details.Candidate.Entity.CandidateRegistration;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CandidateRepo extends JpaRepository<CandidateRegistration,Integer> {

    @Query(value = "select * from candidate where skills_id in (select skills_id from skills_table where skill = ?1)",
            nativeQuery = true)
    public List<CandidateRegistration> findBySkill(String skills);

    @Modifying
    @Query(value="delete from candidate where email =:email",nativeQuery = true)
    Integer deleteByemail(@Param("email")String email);

    CandidateRegistration findByemail(String email);

    CandidateRegistration findByphone(Long phone);


    @Query(value = "select * from candidate where status=:status",nativeQuery = true)
    List<CandidateRegistration> getstatus(String status);
    @Query(value = "select * from candidate where name=:name",nativeQuery = true)
    List<CandidateRegistration> findByName(String name);
    @Query(value = "select * from candidate where name like :name%",nativeQuery = true)
    List<CandidateRegistration> getbyname(String name);

}
