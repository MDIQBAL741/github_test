package candidateproject.details.Candidate.Controller;
import candidateproject.details.Candidate.Dto.CandidateRegistrationDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import candidateproject.details.Candidate.Entity.CandidateStatus;
import candidateproject.details.Candidate.Entity.SkillsList;
import candidateproject.details.Candidate.Handler.FileUploadHandler;
import candidateproject.details.Candidate.Repository.CandidateRepo;
import candidateproject.details.Candidate.Response.ResponseMessage;
import candidateproject.details.Candidate.Services.CandidateServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CandidateController {
    @Autowired
    CandidateServices candidateServices;
    @Autowired
    CandidateRepo candidateRepo;
    @Autowired
    FileUploadHandler fileUploadHandler;

    @PostMapping(value = "/addcandidate",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CandidateRegistration> add(@RequestParam("resume") MultipartFile file,@Valid @ModelAttribute CandidateRegistrationDto candidateRegistrationDto) throws IOException {
        return new ResponseEntity<>(candidateServices.add(file,candidateRegistrationDto),HttpStatus.CREATED);
    }
//    @GetMapping("/files")
//    public ResponseEntity<List<CandidateRegistration>> getListFiles() {
//        List<CandidateRegistration> files = candidateServices.getAllFiles().map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path(dbFile.getName())
//                    .toUriString();
//
//            return files(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getResume()
//                    );
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//
//    }
    @GetMapping("/candidate")
    public List<Object> getcandidate(){
        return candidateServices.getcandidate();
    }

    @PostMapping("/addskills")
    public SkillsList addskills(@RequestBody SkillsList skillsList) {
        return candidateServices.saveskill(skillsList);
    }
    @GetMapping("/getallskills")
    public  List<SkillsList> getallskills(){
        return candidateServices.getallskills();
    }

    @GetMapping("getbyId/{skillsId}")
    public SkillsList getbyId(@PathVariable int skillsId){
        return candidateServices.getbyId(skillsId);
    }

    @PutMapping("updateskill/{skillsId}")
    public SkillsList updateskill(@PathVariable int skillsId,@RequestBody SkillsList skillsList){
        return candidateServices.updateskill(skillsId,skillsList);
    }
//    @GetMapping("/candidate/{email}")
//    public CandidateRegistration findbyemail(@PathVariable String email) {
//        return (CandidateRegistration) candidateServices.findbyemail(email);
//    }

    @GetMapping("/candidates/{phone}")
    public CandidateRegistration findbyPhone(@PathVariable Long phone) {
        return candidateServices.findbyphone(phone);
    }

    @DeleteMapping("/deleteby/{email}")
    public String deletebyemail(@PathVariable String email) {
        return candidateServices.deletebyemail(email);
    }

//    @DeleteMapping("/deletee/{phone}")
//    public String deleteByPhone(@PathVariable Long phone,@RequestBody CandidateRegistration candidateRegistration) {
//        return candidateServices.deletebyphone(phone,candidateRegistration);
//    }

    @PutMapping("/candidateupdate")
    public ResponseEntity<CandidateRegistration> update(@RequestBody CandidateRegistrationDto candidateRegistrationDto) {
        return new ResponseEntity<>(candidateServices.updateCandidate(candidateRegistrationDto), HttpStatus.CREATED);
    }

    //    @PutMapping(value="/addresume",consumes = {MediaType.MULTIPART_MIXED_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
//    produces = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_MIXED_VALUE})
//    public ResponseEntity<CandidateRegistration> updated( @RequestParam("file") MultipartFile file){
//  //      return new ResponseEntity<>(candidateServices.updated(file),HttpStatus.CREATED);
//    }
    @PutMapping("/candidateupdatebyphone")
    public ResponseEntity<CandidateRegistration> updates(@RequestBody CandidateRegistrationDto candidateRegistrationDto) {
        return new ResponseEntity<>(candidateServices.updates(candidateRegistrationDto), HttpStatus.CREATED);
    }

    @GetMapping("/candidateskills/{skills}")
    public List<CandidateRegistration> get(@PathVariable String skills) {
        return candidateServices.findBySkill(skills);
    }
    @DeleteMapping("/deleteskills/{skillsId}")
    public String deleteskill(@PathVariable int skillsId){
        return candidateServices.deleteskills(skillsId);
    }


    //    @PostMapping("/upload")
//    public ResponseEntity<String> uploadfile(@RequestParam("file") MultipartFile file){
//        try {
//            if (file.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file is empty");
//            }
////            if (!file.getContentType().equals("resume/pdf") || !file.getContentType().equals("resume/doc")){
////                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file should be pdf or doc");
////            }
//
//                boolean f=fileUploadHandler.uploadFile(file);
//                if (f)
//                {
////                    return ResponseEntity.ok("file is uploaded sucessfully");
//                    return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/resume/").path(file.getOriginalFilename()).toUriString());
//                }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
//    }
//    @PostMapping(value = "/uploading/{email}")
//    public ResponseEntity<ResponseMessage> uploadingFile(@RequestParam("file") MultipartFile file, @PathVariable String email) {
//        String message = "";
//        try {
//
//            CandidateRegistration cr = candidateServices.findbyemail(email);
//            if (email.equalsIgnoreCase(cr.getEmail())) {
//                cr.setResume(file.getBytes());
//                candidateServices.updateResume(cr, file);
//            }
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//        }
//    }

//    @GetMapping("/files")
//    public ResponseEntity<List<ResponseFile>> getListFiles() {
//        List<ResponseFile> files = candidateServices.getAllFiles().map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getEmail())
//                    .toUriString();
//
//            return new ResponseFile(
//                    dbFile.getName(),
//                    fileDownloadUri);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }
//
@GetMapping("/download/{email}/db")
public ResponseEntity downloadfromdb(@PathVariable String email) {

    CandidateRegistration document = candidateServices.findbyemail(email);
    return (ResponseEntity) ResponseEntity.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
            .body(document.getResume());
}
    @GetMapping ("/currStatus/{status}")
    public List<CandidateRegistration> status(@PathVariable String status){
        return  candidateServices.status(status);
    }

    @PostMapping("/statusupdate")
    public String addstatus(@RequestBody CandidateStatus candidateStatus) {
        return candidateServices.addstatus(candidateStatus);
    }

    @GetMapping("/status/{candidateId}")
    public List<CandidateStatus> find(@PathVariable int candidateId) {
        return candidateServices.find(candidateId);
    }
    @GetMapping("/findbylevel/{status}")
    public List<CandidateStatus> findbylevel(@PathVariable String status){
        return candidateServices.findbylevel(status);
    }
    @GetMapping("/status_findbydate/{candidateId}")
    public CandidateStatus findbydate(@PathVariable int candidateId){
        return candidateServices.findbydate(candidateId);
    }
}