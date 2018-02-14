package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.iv1201.boblaghei.entity.Competence;
import se.kth.iv1201.boblaghei.entity.CompetenceProfile;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;
import se.kth.iv1201.boblaghei.service.ListApplicationService;
import se.kth.iv1201.boblaghei.util.logger.ErrorLogger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller responsible for providing general mappings used both in <code>ListApplicationsView</code> as well
 * as <code>CreateApplicationView</code>
 */
public abstract class AbstractApplicationView {
    @Autowired
    CreateApplicationService createApplicationService;

    @Autowired
    ListApplicationService listApplicationService;

    @Autowired
    ErrorLogger errorLogger;

    abstract String applicationView(Model model);

    Set<CompetenceProfile> selectedCompetences = new HashSet<>();
    Set<Competence> availableCompetences;

    @PostMapping("/add-competence")
    public String addCompetence(Model model, @RequestParam long id, @RequestParam int yearsOfExperience) {
        selectedCompetences.add(new CompetenceProfile(yearsOfExperience, null, findCompetenceWithId(availableCompetences, id)));
        return applicationView(model);
    }

    private Competence findCompetenceWithId(Iterable<Competence> competences, long id){
        for (Competence competence : competences){
            if (competence.getId() == id)
                return competence;
        }
        return null;
    }
}
