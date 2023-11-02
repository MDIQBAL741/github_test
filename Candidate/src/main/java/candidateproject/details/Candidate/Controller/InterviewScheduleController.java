package candidateproject.details.Candidate.Controller;

import candidateproject.details.Candidate.Dto.InterviewScheduleDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.InterviewSchedule;
import candidateproject.details.Candidate.Entity.Panel;
import candidateproject.details.Candidate.Services.InterviewScheduleServices;
import candidateproject.details.Candidate.Services.PanelService;

import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/interview")
public class InterviewScheduleController {
    @Autowired
    InterviewScheduleServices interviewScheduleServices;
    @Autowired
    PanelService panelService;

//    @GetMapping("/candidate/{email}")
//    public CandidateRegistration getcandidate(@PathVariable String email){
//        return interviewScheduleServices.getcandidate(email);
//    }
//    @GetMapping("/getpanelbyskill/{skill}")
//        public List<Panel> getpanel(@PathVariable String skill){
//        return (List<Panel>) interviewScheduleServices.getpanel(skill);
//    }
//    @PostMapping("/scheduleinterview/{email}/{panelEmail}")
//    public String submit(@RequestBody InterviewSchedule interviewSchedule, CandidateStatus candidateStatus, @PathVariable String email, @PathVariable String panelEmail){
//        return interviewScheduleServices.submit(interviewSchedule,candidateStatus,email,panelEmail);
//    }

//    @GetMapping("/candidate/{name}")
//    public List<CandidateRegistration> getcandidates(@PathVariable String name){
//        return interviewScheduleServices.getcandidates(name);
//    }
//    @GetMapping("/panels/{panelName}")
//    public List<Panel> getpanels(@PathVariable String panelName){
//        return interviewScheduleServices.getpanels(panelName);
//    }
    @GetMapping("/all")
    public List<CandidateRegistration> getall(){
        return interviewScheduleServices.getall();
    }
    @GetMapping("allpanel")
    public List<Panel> getallpanel(){
        return interviewScheduleServices.getallpanel();
    }
    @GetMapping("/candidatebyname/{name}")
    public List<CandidateRegistration> gettingcandidates(@PathVariable(name = "name") @Size(min = 3,message = "invalid input") String name){
        return interviewScheduleServices.gettingcandidates(name);
    }
    @GetMapping("/panelbyname/{panelName}")
    public List<Panel> gettingpanels(@PathVariable String panelName){
        return interviewScheduleServices.gettingpanels(panelName);
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
