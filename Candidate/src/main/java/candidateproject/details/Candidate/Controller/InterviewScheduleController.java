package candidateproject.details.Candidate.Controller;

import candidateproject.details.Candidate.Dto.InterviewScheduleDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.InterviewSchedule;
import candidateproject.details.Candidate.Entity.Panel;
import candidateproject.details.Candidate.Services.InterviewScheduleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/interview")
public class InterviewScheduleController {
    @Autowired
    InterviewScheduleServices interviewScheduleServices;

    @GetMapping("/all")
    public List<CandidateRegistration> getall(){
        return interviewScheduleServices.getall();
    }
    @GetMapping("allpanel")
    public List<Panel> getallpanel(){
        return interviewScheduleServices.getallpanel();
    }
    
    @PostMapping("/scheduleInterviewsss")
    public InterviewSchedule scheduleinterviewsss(@RequestBody InterviewScheduleDto interviewScheduleDto){
        return interviewScheduleServices.scheduleinterviewsss(interviewScheduleDto);
    }

    @PostMapping("/scheduleInterview")
    public InterviewSchedule scheduleinterview(@RequestBody InterviewSchedule interviewSchedule){
        return interviewScheduleServices.scheduleinterview(interviewSchedule);
    }
}
