//package candidateproject.details.Candidate.Services;
//
//import candidateproject.details.Candidate.Dto.InterviewScheduleDto;
//import candidateproject.details.Candidate.Dto.ReportRequestDto;
//import candidateproject.details.Candidate.Dto.ReportResponseDto;
//import candidateproject.details.Candidate.Entity.CandidateStatus;
//import candidateproject.details.Candidate.Entity.InterviewSchedule;
//import candidateproject.details.Candidate.Repository.CandidateRepo;
//import candidateproject.details.Candidate.Repository.InterviewScheduleRepo;
//import candidateproject.details.Candidate.Repository.PanelRepository;
//import candidateproject.details.Candidate.Repository.StatusUpdateRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ReportService {
//    @Autowired
//    PanelRepository panelRepository;
//    @Autowired
//    CandidateRepo candidateRepo;
//    @Autowired
//    StatusUpdateRepo statusUpdateRepo;
//    @Autowired
//    InterviewScheduleRepo interviewScheduleRepo;
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
//    public ReportResponseDto getall(ReportResponseDto reportResponseDto){
//
//    }
//
//    public ReportResponseDto candidate(String email, ReportRequestDto reportRequestDto, ReportResponseDto reportResponseDto) {
//         List<CandidateStatus>  exist= statusUpdateRepo.findbyemail(email);
//        List<InterviewSchedule>exist1=interviewScheduleRepo.findByEmail(email);
//        List<String> status = exist.stream().map(c -> c.getStatus()).collect(Collectors.toList());
//        reportResponseDto.setStatus(status);
//        List<String> comment = exist.stream().map(c -> c.getComment()).collect(Collectors.toList());
//        reportResponseDto.setComment(comment);
//        List<LocalDate> date = exist.stream().map(c -> c.getDate()).collect(Collectors.toList());
//        reportResponseDto.setDate(date);
//        List<String> panelEmail = exist1.stream().map(i -> i.getPanelEmail()).collect(Collectors.toList());
//        reportResponseDto.setPanelEmail(panelEmail);
//        List<String> level = exist1.stream().map(i -> i.getLevel()).collect(Collectors.toList());
//        reportResponseDto.setLevel(level);
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
////
////    public ReportResponseDto panel(String email, ReportRequestDto reportRequestDto, ReportResponseDto reportResponseDto) {
////        Panel exist = panelRepository.findByPanelEmail(email);
////        reportResponseDto.setPanelEmail();
////    }
//}
//
////    public List<Object> getall(RepotDto repotDto) {
////        List<CandidateRegistration> exist = candidateRepo.findAll();
////        List<Object> display = exist.stream().mapMulti((candidateRegistration, consumer) -> {
////                    consumer.accept(candidateRegistration.getEmail());
////                    consumer.accept(candidateRegistration.getStatus());
////                })
////                .collect(Collectors.toList());
////        List<InterviewSchedule> exist1 = interviewScheduleRepo.findAll();
//////        List<String> candidateNamesList = exist1.stream().map(InterviewSchedule::getCandidateName).collect(Collectors.toList());
//////        List<String> panelCandidateNamesList = exist.stream().map(CandidateRegistration::getName).collect(Collectors.toList());
////
////        if (exist.stream().map(CandidateRegistration::getName)==
////                exist1.stream().map(InterviewSchedule::getCandidateName)){
////            List<Object> display1 = exist1.stream().mapMulti((interviewSchedule, consumer) -> {
////                consumer.accept(interviewSchedule.getPanelEmail());
////                consumer.accept(interviewSchedule.getDate());
////            })
////                    .collect(Collectors.toList());
////
////            }
////
////        System.out.println(exist);
////        System.out.println(exist1);
////return display;
////
////    }
//
