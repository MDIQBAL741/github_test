package candidateproject.details.Candidate.Controller;

import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.Requirement;
import candidateproject.details.Candidate.Services.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/requirement")
public class RequirementController {
    @Autowired
    RequirementService requirementService;

    @PostMapping("/add")
    public ResponseEntity<Requirement> add(@RequestBody Requirement requirement){
        return new ResponseEntity<>(requirementService.add(requirement), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public String update(@RequestBody Requirement requirement){
        return requirementService.update(requirement);
    }
}
