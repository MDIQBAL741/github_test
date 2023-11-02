package candidateproject.details.Candidate.Repository;

import candidateproject.details.Candidate.Dto.InterviewScheduleDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.InterviewSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InterviewScheduleRepo extends JpaRepository<InterviewSchedule,Integer> {
    @Query(value = "select * from where interview_schedule where date between :date and :date1",nativeQuery = true)
    List<InterviewScheduleDto> findByDate(LocalDate date, LocalDate date1);
    @Query(value = "select * from interview_schedule where candi_email=:email",nativeQuery = true)
    List<InterviewSchedule> findByEmail(String email);


}
