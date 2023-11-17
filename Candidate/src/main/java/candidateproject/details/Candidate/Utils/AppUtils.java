package candidateproject.details.Candidate.Utils;

import candidateproject.details.Candidate.Dto.CandidateRegistrationDto;
import candidateproject.details.Candidate.Entity.CandidateRegistration;
import org.springframework.beans.BeanUtils;


public class AppUtils {
    public static CandidateRegistrationDto entityToDto(CandidateRegistration candidateRegistration){
        CandidateRegistrationDto candidateRegistrationDto=new CandidateRegistrationDto(1, "Internal", "34444", "iqbal", "iqbal@gmail.com", "java", 2, 756898754L, "2", "Hyd", "pune", "NO", "asd", "33", "44", 23, "AXDFV2444F", "Se");
        BeanUtils.copyProperties(candidateRegistration,candidateRegistrationDto);
        return candidateRegistrationDto;
    }
    public static CandidateRegistration DtoToEntity(CandidateRegistrationDto candidateRegistrationDto){
        CandidateRegistration candidateRegistration=new CandidateRegistration();
        BeanUtils.copyProperties(candidateRegistrationDto,candidateRegistration);
        return candidateRegistration;
    }

}
