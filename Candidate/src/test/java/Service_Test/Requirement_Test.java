package Service_Test;

import candidateproject.details.Candidate.Entity.Requirement;
import candidateproject.details.Candidate.Repository.RequirementRepo;
import candidateproject.details.Candidate.Services.RequirementService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Requirement_Test.class)
public class Requirement_Test {
    @Mock
    RequirementRepo requirementRepo;
    @InjectMocks
    RequirementService requirementService;

    @Test
    public void test_update(){
        Requirement requirement=
        new Requirement(2,"Fiserv","Service","Ravi","React",5,"Chennai");
        String projectName = "Service";
        when(requirementRepo.findbyProjectName(projectName)).thenReturn(requirement);
        assertEquals("Updated Sucessfully",requirementService.update(requirement));
    }
}
