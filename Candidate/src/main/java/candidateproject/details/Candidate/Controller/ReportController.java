package candidateproject.details.Candidate.Controller;

import candidateproject.details.Candidate.Dto.InterviewScheduleDto;
import candidateproject.details.Candidate.Dto.ReportRequestDto;
import candidateproject.details.Candidate.Dto.ReportResponseDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.CandidateStatus;
import candidateproject.details.Candidate.Entity.InterviewSchedule;
import candidateproject.details.Candidate.Services.CandidateServices;
import candidateproject.details.Candidate.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/report")
public class ReportController {
    @Autowired
    CandidateServices candidateServices;
    @Autowired
    ReportService reportService;

//    @GetMapping("/all")
//    public ReportResponseDto getall(@RequestBody ReportRequestDto reportRequestDto,ReportResponseDto reportResponseDto){
//        return reportService.getall(reportRequestDto,reportResponseDto);
//    }
//    @GetMapping("/allresponse")
//    public ReportResponseDto get(@RequestBody ReportResponseDto reportResponseDto){
//        return (ReportResponseDto) reportService.getall(reportResponseDto);
//    }
//
//    @GetMapping("/interview")
//    public ReportResponseDto interview(ReportRequestDto reportRequestDto, ReportResponseDto reportResponseDto, @RequestBody InterviewScheduleDto interviewScheduleDto){
//        return reportService.interview(reportRequestDto,reportResponseDto,interviewScheduleDto);
//    }
        @GetMapping("/candidateMail")
        public List<CandidateRegistration> getcandidate(){
           return (List<CandidateRegistration>) reportService.getcandidate();
        }

        @GetMapping("/status_between_dates/{date1}/{date2}")
    public List<CandidateStatus> statusbtwndates(@PathVariable("date1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                                 @PathVariable("date2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2){
            return reportService.statusbtwndates(date1,date2);
        }
}
