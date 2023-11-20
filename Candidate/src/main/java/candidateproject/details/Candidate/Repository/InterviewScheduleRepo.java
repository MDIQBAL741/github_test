package candidateproject.details.Candidate.Repository;

import candidateproject.details.Candidate.Dto.InterviewScheduleDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.InterviewSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface InterviewScheduleRepo extends JpaRepository<InterviewSchedule,Integer> {

    @Query(value = "select * from interview_schedule where candi_email=:email",nativeQuery = true)
    List<InterviewSchedule> findByEmail(String email);
    @Query(value = "select * from interview_schedule where date between :date1 and :date2",nativeQuery = true)
    List<InterviewSchedule> interviewSchedulesbtwndates(Date date1, Date date2);
}
