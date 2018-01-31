package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.iv1201.boblaghei.dto.CompetenceDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceProfileDTO;
import se.kth.iv1201.boblaghei.exception.ApplicationException;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationView {

    @Autowired
    private CreateApplicationService createApplicationService;

    private List<CompetenceProfileDTO> selectedCompetences = new ArrayList<>();
    private List<CompetenceDTO> availableCompetences;

    @GetMapping("/apply")
    public String getApplicationView(Model model) {
        try {
            availableCompetences = createApplicationService.listAllCompetences();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        model.addAttribute("availableCompetences", availableCompetences);
        model.addAttribute("selectedCompetences", selectedCompetences);
        return "apply";
    }

    @PostMapping("/add-competence")
    public String addCompetence(Model model, @RequestParam long id, @RequestParam int yearsOfExperience) {
        selectedCompetences.add(new CompetenceProfileDTO(yearsOfExperience, null, getCompetenceById(id)));
        return getApplicationView(model);
    }

    private CompetenceDTO getCompetenceById(long id){
        for (CompetenceDTO competenceDTO : availableCompetences)
            if (competenceDTO.getId() == id)
                return competenceDTO;
        return null;
    }
}
