package candidateproject.details.Candidate.Services;

import candidateproject.details.Candidate.Dto.PanelDTO;
import candidateproject.details.Candidate.Entity.Panel;
import candidateproject.details.Candidate.Repository.PanelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanelService {
    @Autowired
    PanelRepository panelRepository;
    @Autowired
    ModelMapper modelMapper;

    public Panel addPanel(PanelDTO panelDTO) {
        Panel panelRegistration = modelMapper.map(panelDTO, Panel.class);
        return panelRepository.save(panelRegistration);
    }


    public List<Panel> getPanels() {
        return panelRepository.findAll();

    }

    public Panel getPanelById(int panelId) {

        return panelRepository.findById(panelId).orElse(null);
    }

    public Panel getPanelByEmail(String panelEmail) {
        return panelRepository.findByPanelEmail(panelEmail);
    }


    public Panel updatePanel(PanelDTO panelDTO) {
        Panel panel1=modelMapper.map(panelDTO,Panel.class);
        Panel existingPanelRegistration = panelRepository.findByPanelEmail(panelDTO.getPanelEmail());
//        existingPanelRegistration.setSkillsId(panel1.getSkillsId());
        existingPanelRegistration.setStartTime(panel1.getStartTime());
        existingPanelRegistration.setEndTime(panel1.getEndTime());
        existingPanelRegistration.setNumberOfSlots(panel1.getNumberOfSlots());
        existingPanelRegistration.setLevel(panel1.getLevel());
        // existingPanelRegistration.setScheduleTimeSlots(panel.getScheduleTimeSlots());
        return panelRepository.save(existingPanelRegistration);
    }


    public String deletePanel(String panelEmail) {
        Integer exist= panelRepository.deleteByemail(panelEmail);
        if (exist==0) {
            return "unknown email";
        }
        return "candidate deleted";

    }

    public List<Panel> getPanelBySkill(String skill) {
        List<Panel> panels= panelRepository.findBySkillId(skill);
        return panels;
    }
}