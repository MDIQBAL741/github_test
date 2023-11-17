package candidateproject.details.Candidate.Services;

import candidateproject.details.Candidate.Dto.InterviewScheduleDto;
import candidateproject.details.Candidate.Dto.ReportRequestDto;
import candidateproject.details.Candidate.Dto.ReportResponseDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.CandidateStatus;
import candidateproject.details.Candidate.Entity.InterviewSchedule;
import candidateproject.details.Candidate.Entity.Panel;
import candidateproject.details.Candidate.Repository.CandidateRepo;
import candidateproject.details.Candidate.Repository.InterviewScheduleRepo;
import candidateproject.details.Candidate.Repository.PanelRepository;
import candidateproject.details.Candidate.Repository.StatusUpdateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    PanelRepository panelRepository;
    @Autowired
    CandidateRepo candidateRepo;
    @Autowired
    StatusUpdateRepo statusUpdateRepo;
    @Autowired
    InterviewScheduleRepo interviewScheduleRepo;

//
//    public ReportResponseDto getall(ReportRequestDto reportRequestDto, ReportResponseDto reportResponseDto) {
//
//        List<CandidateRegistration> exist = candidateRepo.findAll();
//        List<String> candidateEmail = exist.stream().map(c -> c.getEmail()).collect(Collectors.toList());
//        reportResponseDto.setCandidateEmail(candidateEmail);
//        List<String> status=exist.stream().map(c->c.getStatus()).collect(Collectors.toList());
//        reportResponseDto.setStatus(status);
//        List<Panel> exist1 = panelRepository.findAll();
//        List<String> panelEmail = exist1.stream().map(p -> p.getPanelEmail()).collect(Collectors.toList());
//        reportResponseDto.setPanelEmail(panelEmail);
//        System.out.println(candidateEmail+" "+status+" "+panelEmail);
//        return reportResponseDto;
//    }
//


//    public ReportResponseDto interview(ReportRequestDto reportRequestDto, ReportResponseDto reportResponseDto, InterviewScheduleDto interviewScheduleDto) {
//        List<InterviewScheduleDto> dates= interviewScheduleRepo.findByDate(interviewScheduleDto.getDate(),interviewScheduleDto.getDate1());
//        reportResponseDto.setCandidateEmail(dates.stream().map(i-> i.getCandiEmail()).collect(Collectors.toList()));
//        reportResponseDto.setPanelEmail(dates.stream().map(i->i.getPanelEmail()).collect(Collectors.toList()));
//        reportResponseDto.setStatus(dates.stream().map(i->i.getLevel()).collect(Collectors.toList()));
////        reportResponseDto.setDate(dates.stream().map(i->i.getDate()).collect(Collectors.toList()));
//    return reportResponseDto;
//    }

//    public List<Object> getall(ReportResponseDto repotDto) {
//        List<CandidateRegistration> exist = candidateRepo.findAll();
//        List<Object> display = exist.stream().mapMulti((candidateRegistration, consumer) -> {
//                    consumer.accept(candidateRegistration.getEmail());
//                    consumer.accept(candidateRegistration.getStatus());
//                })
//                .collect(Collectors.toList());
//        List<InterviewSchedule> exist1 = interviewScheduleRepo.findAll();
////        List<String> candidateNamesList = exist1.stream().map(InterviewSchedule::getCandidateName).collect(Collectors.toList());
////        List<String> panelCandidateNamesList = exist.stream().map(CandidateRegistration::getName).collect(Collectors.toList());
//
//        if (exist.stream().map(CandidateRegistration::getName)==
//                exist1.stream().map(InterviewSchedule::getCandidateName)){
//            List<Object> display1 = exist1.stream().mapMulti((interviewSchedule, consumer) -> {
//                consumer.accept(interviewSchedule.getPanelEmail());
//                consumer.accept(interviewSchedule.getDate());
//            })
//                    .collect(Collectors.toList());
//
//            }
//
//        System.out.println(exist);
//        System.out.println(exist1);
//return display;
//
//    }
public List<CandidateRegistration> getcandidate() {
    return  candidateRepo.findAll();
}
public List<CandidateStatus> statusbtwndates(Date date1, Date date2) {
    return statusUpdateRepo.statusbtwndates(date1,date2);
}

}