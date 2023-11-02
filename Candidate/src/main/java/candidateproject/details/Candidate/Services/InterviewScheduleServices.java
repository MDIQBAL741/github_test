package candidateproject.details.Candidate.Services;

import candidateproject.details.Candidate.Dto.InterviewScheduleDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.InterviewSchedule;
import candidateproject.details.Candidate.Entity.Panel;
import candidateproject.details.Candidate.Repository.CandidateRepo;
import candidateproject.details.Candidate.Repository.InterviewScheduleRepo;
import candidateproject.details.Candidate.Repository.PanelRepository;
import candidateproject.details.Candidate.Repository.StatusUpdateRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewScheduleServices {
    @Autowired
    CandidateRepo candidateRepo;
    @Autowired
    PanelRepository panelRepository;
    @Autowired
    InterviewScheduleRepo interviewScheduleRepo;
    @Autowired
    StatusUpdateRepo statusUpdateRepo;
    @Autowired
    ModelMapper modelMapper;


//    public CandidateRegistration getcandidate(String email) {
//        CandidateRegistration candidate=candidateRepo.findByemail(email);
//        return candidate;
//    }
//
//    public List<Panel> getpanel(String skill) {
//       List<Panel> exist= panelRepository.findBySkillId(skill);
//       return exist;
//    }
//
//    public String submit(InterviewSchedule interviewSchedule, CandidateStatus candidateStatus, String email, String panelEmail) {
//          CandidateRegistration exist= candidateRepo.findByemail(email);
//          Panel exist1=panelRepository.findByPanelEmail(panelEmail);
//          if (exist!=null&&exist1!=null&&exist1.getNumberOfSlots()>0) {
//              interviewSchedule.setCandidateName(exist.getName());
//              interviewSchedule.setCandiEmail(exist.getEmail());
//              interviewSchedule.setPanelName(exist1.getPanelName());
//              interviewSchedule.setPanelEmail(exist1.getPanelEmail());
//              interviewSchedule.setStartTime(exist1.getStartTime());
//              interviewSchedule.setEndTime(exist1.getEndTime());
//              interviewSchedule.setDate(LocalDate.now());
//              exist1.setNumberOfSlots( (exist1.getNumberOfSlots())-1);
//              interviewScheduleRepo.save(interviewSchedule);
//              exist.setStatus(interviewSchedule.getLevel());
//              candidateRepo.save(exist);
//              panelRepository.save(exist1);
//              candidateStatus.setEmail(exist.getEmail());
//              candidateStatus.setStatus(interviewSchedule.getLevel());
//              candidateStatus.setDate(LocalDate.now());
//              statusUpdateRepo.save(candidateStatus);
//              return "Interview Schedule";
//          }
//          else return "Interview Not Schedule";
//    }
//
//    public List<CandidateRegistration> getcandidates(String name) {
//        return (List<CandidateRegistration>) candidateRepo.findByName(name);
//    }
//
//    public List<Panel> getpanels(String panelName) {
//        return panelRepository.findByname(panelName);
//    }

    public List<CandidateRegistration> gettingcandidates(String name) {
        return candidateRepo.getbyname(name);
    }

    public List<Panel> gettingpanels(String panelName) {
        return panelRepository.getbyname(panelName);
    }


    public List<CandidateRegistration> getall() {
        return candidateRepo.findAll();
    }

    public List<Panel> getallpanel() {
        return panelRepository.findAll();
    }

    public InterviewSchedule scheduleinterviewsss(InterviewScheduleDto interviewScheduleDto) {
        InterviewSchedule interviewSchedule = modelMapper.map(interviewScheduleDto, InterviewSchedule.class);
      Panel panel1=panelRepository.findByPanelEmail(interviewScheduleDto.getPanelEmail());
      if (panel1.getNumberOfSlots()>0){
          panel1.setNumberOfSlots((panel1.getNumberOfSlots())-1);
          panelRepository.save(panel1);
          return interviewScheduleRepo.save(interviewSchedule);
      }
      else return null;
    }


    public InterviewSchedule scheduleinterview(InterviewSchedule interviewSchedule) {
        return interviewScheduleRepo.save(interviewSchedule);
    }
}
