//package candidateproject.details.Candidate.Controller;
//
//import candidateproject.details.Candidate.Dto.InterviewScheduleDto;
//import candidateproject.details.Candidate.Dto.ReportRequestDto;
//import candidateproject.details.Candidate.Dto.ReportResponseDto;
//import candidateproject.details.Candidate.Entity.InterviewSchedule;
//import candidateproject.details.Candidate.Services.CandidateServices;
//import candidateproject.details.Candidate.Services.ReportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/report")
//public class ReportController {
//    @Autowired
//    CandidateServices candidateServices;
//    @Autowired
//    ReportService reportService;
//
////    @GetMapping("/all")
////    public ReportResponseDto getall(@RequestBody ReportRequestDto reportRequestDto,ReportResponseDto reportResponseDto){
////        return reportService.getall(reportRequestDto,reportResponseDto);
////    }
//    @GetMapping("/all")
//    public ReportResponseDto get(@RequestBody ReportResponseDto reportResponseDto){
//        return reportService.getall(reportResponseDto);
//    }
//    @GetMapping("/candidate/{email}")
//    public ReportResponseDto candidate(@PathVariable String email,@RequestBody ReportRequestDto reportRequestDto,ReportResponseDto reportResponseDto){
//        return reportService.candidate(email,reportRequestDto,reportResponseDto);
//    }
//    @GetMapping("/interview")
//    public ReportResponseDto interview(ReportRequestDto reportRequestDto, ReportResponseDto reportResponseDto, @RequestBody InterviewScheduleDto interviewScheduleDto){
//        return reportService.interview(reportRequestDto,reportResponseDto,interviewScheduleDto);
//    }
////    @GetMapping("/panel/{email}")
////    public ReportResponseDto panel(@PathVariable String email,@RequestBody ReportRequestDto reportRequestDto,ReportResponseDto reportResponseDto ){
////        return reportService.panel(email,reportRequestDto,reportResponseDto);
////    }
//
//}
