package se.kth.iv1201.boblaghei.view;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.boblaghei.dto.ApplicationDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceProfileDTO;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;
import se.kth.iv1201.boblaghei.util.DateUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller responsible for providing mappings used for searching and listing applications.
 */
@Controller
@RequestMapping("/recruiter/applications")
public class ListApplicationsView extends AbstractApplicationView {

    /**
     * Invocated when a GET request is sent to "/recruiter/applications". Loads all competences from the database as well
     * as the competences that the user has chosen for search.
     * @param model is responsible for making data available in the view.
     * @return the listApplications.html page
     */
    @GetMapping
    public String applicationView(Model model) {
        availableCompetences = createApplicationService.listAllCompetences();
        model.addAttribute("availableCompetences", availableCompetences);
        model.addAttribute("selectedCompetences", selectedCompetences);
        model.addAttribute("applicationSearchDto", new ApplicationSearchDTO());
        return "listApplications";
    }

    /**
     * Invocated when a POST request is sent to "/recruiter/applications". Takes parameters from the search fields and
     * builds an <code>ApplicationSearchDTO</code> that is used in <code>findApplications</code> to return all applications
     * matching the search parameters. These are then loaded into the model and the view is loaded.
     * @param model is responsible for making data available in the view.
     * @param dto contains the search criteria
     * @return the return value from the applicationView method, see @applicationView.
     */
    @PostMapping
    public String search(Model model, @ModelAttribute ApplicationSearchDTO dto) {
        System.out.println(dto);
        List<ApplicationDTO> applications = listApplicationService.findApplications(dto);
        model.addAttribute("listOfApplications", applications);
        return applicationView(model);
    }

    /**
     * Invocated when a GET-request is sent to "/recruiter/applications/id". Responsible for loading a single application
     * view
     * @param model responsible for making data available in the view
     * @param id the id of the application to show
     * @return the singleApplication.html page
     */
    @GetMapping("/{id}")
    public String viewSingleApplication(Model model, @PathVariable("id") long id) {
        ApplicationDTO application = listApplicationService.findApplicationById(id);
        model.addAttribute("singleApplication", application);
        return "singleApplication";
    }
}
