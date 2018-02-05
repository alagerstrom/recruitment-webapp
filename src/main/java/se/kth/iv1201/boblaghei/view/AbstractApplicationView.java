package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.iv1201.boblaghei.dto.CompetenceDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceProfileDTO;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;
import se.kth.iv1201.boblaghei.service.ListApplicationService;
import se.kth.iv1201.boblaghei.util.logger.ErrorLogger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractApplicationView {
    @Autowired
    CreateApplicationService createApplicationService;

    @Autowired
    ListApplicationService listApplicationService;

    @Autowired
    ErrorLogger errorLogger;

    abstract String applicationView(Model model);

    List<CompetenceProfileDTO> selectedCompetences = new ArrayList<>();
    List<CompetenceDTO> availableCompetences;

    CompetenceDTO getCompetenceById(long id) {
        for (CompetenceDTO competenceDTO : availableCompetences)
            if (competenceDTO.getId() == id)
                return competenceDTO;
        return null;
    }

    @PostMapping("/add-competence")
    public String addCompetence(Model model, @RequestParam long id, @RequestParam int yearsOfExperience) {
        selectedCompetences.add(new CompetenceProfileDTO(yearsOfExperience, null, getCompetenceById(id)));
        return applicationView(model);
    }
}
