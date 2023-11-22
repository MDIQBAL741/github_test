package candidateproject.details.Candidate.Repository;

import candidateproject.details.Candidate.Entity.CandidateStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface StatusUpdateRepo extends JpaRepository<CandidateStatus,Integer> {
    @Query(value = "select * from candidatestatus where candidate_id =?1", nativeQuery = true)
    List<CandidateStatus> findAllById(int candidateId);
    @Query(value = "select * from candidatestatus where status=:status",nativeQuery = true)
    List<CandidateStatus> findbylevel(String status);
    @Query(value = "select * from candidatestatus where date_time=(select max(date_time) from candidate_status where candidate_id=?1)",nativeQuery = true)
    CandidateStatus findbydate(int candidateId);
    @Modifying
    @Query(value = "delete from candidatestatus where email=:email",nativeQuery = true)
    void deletebyemail(String email);
    @Query(value = "select * from candidatestatus where email=:email",nativeQuery = true)
    List<CandidateStatus> findbyemail(String email);
    @Query(value = "select * from candidatestatus where date between :date1 and :date2",nativeQuery = true)
    List<CandidateStatus> statusbtwndates(@PathVariable("date1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                          @PathVariable("date2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2);
    @Query(value = "select * from candidatestatus where status=:status",nativeQuery = true)
    List<CandidateStatus> statusbystatus(String status);
}
