package candidateproject.details.Candidate.Services;

import candidateproject.details.Candidate.Dto.CandidateRegistrationDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.CandidateStatus;
import candidateproject.details.Candidate.Entity.SkillsList;
import candidateproject.details.Candidate.Repository.CandidateRepo;
import candidateproject.details.Candidate.Repository.SkillsRepo;
import candidateproject.details.Candidate.Repository.StatusUpdateRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class CandidateServices {
    @Autowired
    CandidateRepo candidateRepo;
    @Autowired
    SkillsRepo skillsRepo;
    @Autowired
    StatusUpdateRepo statusUpdateRepo;
    @Autowired
    private ModelMapper modelMapper;

    public CandidateRegistration add(MultipartFile file,CandidateRegistrationDto candidateRegistrationDto) throws IOException {
        CandidateRegistration candidate=modelMapper.map(candidateRegistrationDto,CandidateRegistration.class);
        String fileName=StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);
        candidate.setResume(file.getBytes());
        return candidateRepo.save(candidate);
    }
    public List<Object> getcandidate() {
         List<CandidateRegistration> candidates=candidateRepo.findAll();
         return Collections.singletonList(candidates);
    }

    public SkillsList saveskill(SkillsList skillsList) {

        return skillsRepo.save(skillsList);
    }
    public SkillsList updateskill(int skillsId, SkillsList skillsList) {
        SkillsList skillsList1= skillsRepo.getbyskillId(skillsId);
        skillsList1.setSkill(skillsList.getSkill());
        return skillsRepo.save(skillsList1);
    }

    public CandidateRegistration findbyemail(String email) {
        CandidateRegistration exist= candidateRepo.findByemail(email);
        if (email.equals(exist)) {
            return exist;
        }
        return exist;
    }
    public CandidateRegistration findbyphone(Long phone) {
        return (CandidateRegistration) candidateRepo.findByphone(phone).orElse(null);
    }
    public String deletebyemail(String email) {
        statusUpdateRepo.deletebyemail(email);
        Integer delete =candidateRepo.deleteByemail(email);
        if (delete==0){
            return "email not exist";
        }
        else
            return "candidate deleted";
    }

    public CandidateRegistration updateCandidate(CandidateRegistrationDto candidateRegistrationDto) {
        CandidateRegistration candidate = modelMapper.map(candidateRegistrationDto, CandidateRegistration.class);
        CandidateRegistration existing = (CandidateRegistration) candidateRepo.findByemail(candidate.getEmail());
        existing.setNoticePeriod(candidate.getNoticePeriod());
        existing.setStatus(candidate.getStatus());
        return candidateRepo.save(existing);
    }
    public CandidateRegistration updateResume(CandidateRegistration candidate,MultipartFile file){
        CandidateRegistration existing= (CandidateRegistration) candidateRepo.findByemail(candidate.getEmail()).orElse(new CandidateRegistration());
        existing.setResume(candidate.getResume());
        return candidateRepo.save(existing);
    }

    public CandidateRegistration store(MultipartFile file,String email) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        CandidateRegistration FileDB = new CandidateRegistration(fileName,file.getName(), file.getBytes());

        return candidateRepo.save(FileDB);
    }

    public String addstatus(CandidateStatus candidateStatus) {

        CandidateRegistration exist=candidateRepo.findByemail(candidateStatus.getEmail());
        if (exist!=null){
            candidateStatus.setDate(LocalDate.now());
            exist.setStatus(candidateStatus.getStatus());
            statusUpdateRepo.save(candidateStatus);
            candidateStatus.setEmail(exist.getEmail());
            candidateStatus.setCandidate_Id(exist.getCandidateId());
            candidateRepo.save(exist);
            return "saved";
        }
        else return null;
    }

    public List<CandidateStatus> findbylevel(String status) {
        return statusUpdateRepo.findbylevel(status);
    }

    public CandidateStatus findbydate(int candidateId) {
        return statusUpdateRepo.findbydate(candidateId);
    }

    public List<CandidateRegistration> status(String status) {
        return  candidateRepo.getstatus(status);
    }


    public List<SkillsList> getallskills() {
        return skillsRepo.findAll();
    }

    public String deleteskills(int skillsId) {
       SkillsList exist=skillsRepo.getbyskill(skillsId);
       if (exist!=null)
       {
           skillsRepo.deletebyskill(skillsId);
       }
           return "skill deleted";
    }

    public SkillsList getbyId(int skillsId) {
       return skillsRepo.getbyskillId(skillsId);
    }
}
