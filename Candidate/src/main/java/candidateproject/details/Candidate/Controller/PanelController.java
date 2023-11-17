package candidateproject.details.Candidate.Controller;

import candidateproject.details.Candidate.Dto.PanelDTO;
import candidateproject.details.Candidate.Entity.Panel;
import candidateproject.details.Candidate.Services.PanelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PanelController {
    @Autowired
    PanelService panelService;


    @PostMapping("/addpanel")
    public ResponseEntity<Panel> addpanel(@Valid @RequestBody PanelDTO panelDTO){
        return new ResponseEntity<>(panelService.addPanel(panelDTO),HttpStatus.CREATED);
    }


    @GetMapping("/panels")
    public List<Panel> findAllPanel(){
        return panelService.getPanels();
    }


    @GetMapping("/panel/{panelEmail}")
    public Panel findPanelByEmail(@PathVariable("panelEmail") String panelEmail) {
        return panelService.getPanelByEmail(panelEmail);
    }

    @PutMapping("/update")
    public ResponseEntity<Panel> updatePanel(@RequestBody PanelDTO panelDTO){
        return new ResponseEntity<>(panelService.updatePanel(panelDTO),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{panelEmail}")
    public String deletePanel(@PathVariable String panelEmail){
        return panelService.deletePanel(panelEmail);
    }



}
