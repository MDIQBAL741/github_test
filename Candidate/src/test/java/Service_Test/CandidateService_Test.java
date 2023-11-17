package Service_Test;
import candidateproject.details.Candidate.Dto.CandidateRegistrationDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.SkillsList;
import candidateproject.details.Candidate.Repository.CandidateRepo;
import candidateproject.details.Candidate.Repository.SkillsRepo;
import candidateproject.details.Candidate.Services.CandidateServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CandidateService_Test.class)
public class CandidateService_Test {
    @Mock
    CandidateRepo candidateRepo;
    @Mock
    SkillsRepo skillsRepo;
    @InjectMocks
    CandidateServices candidateServices;
//    public CandidateRegistration candidate;
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
    public void test_addCandidate(){
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
//        when(candidateRepo.save(candidateRegistrationDto)).thenReturn(candidateRegistrationDto);
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
        when(candidateRepo.findByphone(candidateRegistration.getPhone())).thenReturn(candidateRegistration);
        Long phone=candidateRegistration.getPhone();
        assertEquals(8767890987L,candidateServices.findbyphone(candidateRegistration).getPhone());
    }
}