package Service_Test;
import candidateproject.details.Candidate.Dto.CandidateRegistrationDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.CandidateStatus;
import candidateproject.details.Candidate.Entity.SkillsList;
import candidateproject.details.Candidate.Repository.CandidateRepo;
import candidateproject.details.Candidate.Repository.SkillsRepo;
import candidateproject.details.Candidate.Repository.StatusUpdateRepo;
import candidateproject.details.Candidate.Services.CandidateServices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CandidateService_Test.class)
public class CandidateService_Test {
    @Mock
    CandidateRepo candidateRepo;
    @Mock
    SkillsRepo skillsRepo;
    @Mock
    StatusUpdateRepo statusUpdateRepo;
    @InjectMocks
    CandidateServices candidateServices;
//    public CandidateRegistration candidate;
    public CandidateRegistrationDto candidateRegistrationDto;
    public CandidateRegistration candidateRegistration;
    public CandidateStatus candidateStatus;
    public SkillsList skillsList;

    @Test
    public void test_getcandidate(){
        CandidateRegistrationDto candidateRegistrationDto= CandidateRegistrationDto.builder()
                .candidateType("Internal").name("Iqbal").phone("8798767898").email("iqbal@gmail.com")
                .level(2).pancard("AASDF6999J").employeeID("987676").build();
        when(candidateRepo.findAll()).thenReturn((List) Collections.singletonList(candidateRegistrationDto));
        assertEquals(1,candidateServices.getcandidate().size());
    }
    @Test
    public void test_addCandidate() throws IOException {
        MockMultipartFile file = null;

        CandidateRegistrationDto candidateRegistrationDto= CandidateRegistrationDto.builder()
                .candidateType("Internal").name("Iqbal").phone("8798767898").email("iqbal@gmail.com")
                .level(2).pancard("AASDF6999J").employeeID("987676").resume(new MultipartFile() {
                    public MultipartFile file(MockMultipartFile file) {

                               file = new MockMultipartFile(
                                "file",
                                "hello.txt",
                                MediaType.TEXT_PLAIN_VALUE,
                                "Hello, World!".getBytes()
                        );
                               return file;
                    }

                    @Override
                    public String getName() {
                        return null;
                    }

                    @Override
                    public String getOriginalFilename() {
                        return null;
                    }

                    @Override
                    public String getContentType() {
                        return null;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public long getSize() {
                        return 0;
                    }

                    @Override
                    public byte[] getBytes() throws IOException {
                        return new byte[0];
                    }

                    @Override
                    public InputStream getInputStream() throws IOException {
                        return null;
                    }

                    @Override
                    public void transferTo(File dest) throws IOException, IllegalStateException {

                    }
                }.file(file)).build();
        when(candidateRepo.save(candidateRegistration)).then((Answer<?>) candidateRegistrationDto);
//        assertEquals(1,candidateServices.add(file,candidateRegistrationDto));
    }

    @Test
    public void test_getskillbyId(){
        SkillsList skillsList=SkillsList.builder().skillsId(1).skill("Java").skillsId(2).skill("Python").build();
        int skillId=2;
        when(skillsRepo.getbyskillId(skillId)).thenReturn((SkillsList) skillsList);
        assertEquals("Python",candidateServices.getbyId(2).getSkill());

    }
    @Test
    public void test_currentstatus(){
        CandidateRegistration candidateRegistration=CandidateRegistration.builder()
                .name("Iqbal").email("iqbal@gmail.com").pancard("DDFGE4555T").phone(8767890987L).status("L1-Selected")
                .build();
        String status="L1-Selected";
        when(candidateRepo.getstatus(status)).thenReturn(Collections.singletonList(candidateRegistration));
        assertEquals(Collections.singletonList(candidateRegistration),candidateServices.status(status));
    }
    @Test
    public void test_Listcurrentstatus(){
        List<CandidateRegistration> candidateRegistration=new ArrayList<CandidateRegistration>();
        candidateRegistration.add(new CandidateRegistration("md","md@gmail.com",8988789878L,"L1-selected"));
        candidateRegistration.add(new CandidateRegistration("iqbal","iqbal@gmail.com",8988789978L,"L1-selected"));
        String status="L1-Selected";
        when(candidateRepo.getstatus(status)).thenReturn(candidateRegistration);
        assertEquals(2,candidateServices.status(status).size());
    }
    @Test
    public void test_saveskill(){
        SkillsList skillsList= SkillsList.builder().skillsId(1).skill("Java").build();
        when(skillsRepo.save(skillsList)).thenReturn(skillsList);
        assertEquals("Java",candidateServices.saveskill(skillsList).getSkill());
    }
    @Test
    public void test_updateskill(){

        SkillsList skillsList1= SkillsList.builder().skillsId(1).skill("Java").build();
        int skillsId=skillsList1.getSkillsId();
        when(skillsRepo.getbyskillId(skillsList1.getSkillsId())).thenReturn(skillsList1);
        skillsList1.setSkill("Python");
        skillsList1.setSkill(skillsList1.getSkill());
        when(skillsRepo.save(skillsList1)).thenReturn(skillsList1);
        assertEquals("Python",candidateServices.updateskill(skillsId, skillsList1).getSkill());
    }
    @Test
    public void test_findbyphone(){
        CandidateRegistration candidateRegistration=CandidateRegistration.builder()
                .name("Iqbal").email("iqbal@gmail.com").pancard("DDFGE4555T").phone(8767890987L).status("L1-Selected")
                .build();
        Long phone=8767890987L;
        when(candidateRepo.findByphone(phone)).thenReturn((CandidateRegistration) candidateRegistration);

        assertEquals(8767890987L,candidateServices.findbyphone(phone));
    }

    @Test
    public void test_deletebyemail(){
        CandidateRegistration candidateRegistration=CandidateRegistration.builder()
                .name("Iqbal").email("iqbal@gmail.com").pancard("DDFGE4555T").phone(8767890987L).status("L1-Selected")
                .build();
        String email="iqba1@gmail.com";
        when(candidateRepo.findByemail(email)).thenReturn(candidateRegistration);
        if (email!=candidateRegistration.getEmail()){
            assertEquals("candidate not exist","candidate not exist");
        }
        else {
            when(candidateRepo.deleteByemail(email)).thenReturn(null);
//            when(statusUpdateRepo.deletebyemail(email)).thenReturn(null);
            assertEquals("candidate deleted sucessfully", candidateServices.deletebyemail(email));
        }
    }
    @Test
    public void test_updatecandidate(){
        CandidateRegistration candidateRegistration=CandidateRegistration.builder()
                .name("Iqbal").email("iqbal@gmail.com").pancard("DDFGE4555T").phone(8767890987L).status("L1-Selected").noticePeriod("25")
                .build();
        String email="iqba1@gmail.com";
        when(candidateRepo.findByemail(email)).thenReturn(candidateRegistration);
        candidateRegistration.setNoticePeriod("10");
        candidateRegistration.setStatus("L2-Selected");
        when(candidateRepo.save(candidateRegistration)).thenReturn(candidateRegistration);
        assertEquals("L2-Selected",candidateServices.updateCandidate(candidateRegistrationDto).getStatus());
    }
    @Test
    public void test_addstatus(){
        CandidateRegistration candidateRegistration=CandidateRegistration.builder()
                .name("Iqbal").email("iqbal@gmail.com").pancard("DDFGE4555T").phone(8767890987L).status("L1-Selected").noticePeriod("25")
                .build();
        CandidateStatus candidateStatus= CandidateStatus.builder().email("iqbal@gmail.com").status("L2-Selected").comment("Good").build();
        String email= candidateStatus.getEmail();
        when(candidateRepo.findByemail(email)).thenReturn(candidateRegistration);
        if(candidateRegistration.getEmail()!=null){
            candidateStatus.setDate(LocalDate.now());
        candidateRegistration.setStatus(candidateStatus.getStatus());
            candidateStatus.setEmail(candidateRegistration.getEmail());
            candidateStatus.setCandidate_Id(candidateStatus.getCandidate_Id());
            when(statusUpdateRepo.save(candidateStatus)).thenReturn(candidateStatus);
            when(candidateRepo.save(candidateRegistration)).thenReturn(candidateRegistration);
            assertEquals("saved",candidateServices.addstatus(candidateStatus));
        }
}
        @Test
                public void test_findbylevel() {
            List<CandidateStatus> candidateStatusList = new ArrayList<CandidateStatus>();
            candidateStatusList.add(new CandidateStatus(1,"iqbal","iqbal@gmail.com","selected",1,LocalDate.now(),"good"));
            candidateStatusList.add(new CandidateStatus(2,"md","md@gmail.com","selected",1,LocalDate.now(),"ex"));
            List<CandidateStatus> candidateStatuses= (List<CandidateStatus>) candidateStatusList.stream().filter(e->"selected".equals(e.getStatus())).collect(Collectors.toList());
            String status= candidateStatuses.stream().map(e->e.getStatus()).toString();
            System.out.println(status);
            when(statusUpdateRepo.findbylevel(status)).thenReturn(candidateStatuses);
            assertEquals(2,candidateServices.findbylevel(status).size());
        }

        @Test
    public void test_findbydate(){
            List<CandidateStatus> candidateStatusList = new ArrayList<CandidateStatus>();
            candidateStatusList.add(new CandidateStatus(1,"iqbal","iqbal@gmail.com","selected",1,LocalDate.parse("2023-11-17"),"good"));
            candidateStatusList.add(new CandidateStatus(2,"iqbal","iqbal@gmail.com","Rejected",1,LocalDate.now(),"ex"));
            CandidateStatus ids= candidateStatusList.stream().max((e1, e2) -> e1.getDate().compareTo(e2.getDate())).get();
            int id= ids.getCandidate_Id();
            when(statusUpdateRepo.findbydate(id)).thenReturn(ids);
            assertEquals("Rejected",candidateServices.findbydate(id).getStatus());
        }

        @Test
    public void test_getallskills(){
        List<SkillsList> skillsList1=new ArrayList<SkillsList>();
        skillsList1.add(new SkillsList(1,"Java"));
        skillsList1.add(new SkillsList(2,"Python"));
        when(skillsRepo.findAll()).thenReturn(skillsList1);
        assertEquals(2,candidateServices.getallskills().size());
        }

        @Test
    public void test_deleteskills() {
            List<SkillsList> skillsList1 = new ArrayList<SkillsList>();
            skillsList1.add(new SkillsList(1, "Java"));
            skillsList1.add(new SkillsList(2, "Python"));
            int skillid = 2;
            candidateServices.deleteskills(skillid);
            verify(skillsRepo,times(1)).deletebyskill(skillid);
        }
}