package candidateproject.details.Candidate.Services;

import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.Requirement;
import candidateproject.details.Candidate.Repository.CandidateRepo;
import candidateproject.details.Candidate.Repository.RequirementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementService {
@Autowired
    RequirementRepo requirementRepo;
    CandidateRepo candidateRepo;

    public Requirement add(Requirement requirement){
        return requirementRepo.save(requirement);
    }

    public Requirement update() {
        List<CandidateRegistration> exist = candidateRepo.findAll();

//        String exist1 = candidateRegistration.getStatus();
//        if (exist1=="Onboard"){
//            requirement.setTotalPosition((requirement.getTotalPosition())-1);
//        }
        return (Requirement) exist;
    }
}
