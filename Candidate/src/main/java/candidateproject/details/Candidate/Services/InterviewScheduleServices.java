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

import java.util.Date;
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
