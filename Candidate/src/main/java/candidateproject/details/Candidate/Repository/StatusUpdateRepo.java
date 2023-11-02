package candidateproject.details.Candidate.Repository;

import candidateproject.details.Candidate.Entity.CandidateStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface StatusUpdateRepo extends JpaRepository<CandidateStatus,Integer> {
    @Query(value = "select * from candidate_status where candidate_id =?1", nativeQuery = true)
    List<CandidateStatus> findAllById(int candidateId);
    @Query(value = "select * from candidate_status where status=:status",nativeQuery = true)
    List<CandidateStatus> findbylevel(String status);
    @Query(value = "select * from candidate_status where date_time=(select max(date_time) from candidate_status where candidate_id=?1)",nativeQuery = true)
    CandidateStatus findbydate(int candidateId);
    @Modifying
    @Query(value = "delete from candidate_status where email=:email",nativeQuery = true)
    void deletebyemail(String email);
    @Query(value = "select * from candidate_status where email=:email",nativeQuery = true)
    List<CandidateStatus> findbyemail(String email);


//    @Modifying
//    @Query(value = "delete from candidate_status where phone:phone",nativeQuery = true)
//    void deletebyphone(Long phone);
}
