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

    public String update(Requirement requirement) {
      Requirement requirement1 = requirementRepo.findbyProjectName(requirement.getProjectName());
        if (!(requirement1 == null)) {
            requirement1.setManagerName(requirement.getManagerName());
            requirement1.setProjectLocation(requirement.getProjectLocation());
            requirement1.setSkill(requirement.getSkill());
            requirement1.setTotalPosition(requirement.getTotalPosition());
            requirementRepo.save(requirement1);
        }else {
            return "Invalid ProjectName";
        }
        return "Updated Sucessfully";
    }
}
