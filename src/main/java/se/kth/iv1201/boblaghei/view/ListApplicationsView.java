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
        return "listApplications";
    }

    /**
     * Invocated when a POST request is sent to "/recruiter/applications". Takes parameters from the search fields and
     * builds an <code>ApplicationSearchDTO</code> that is used in <code>findApplications</code> to return all applications
     * matching the search parameters. These are then loaded into the model and the view is loaded.
     * @param model is responsible for making data available in the view.
     * @param from date from which the applicant should be available.
     * @param to date to which the applicant should be available.
     * @param created date on which application should be created.
     * @param firstName applicants first name.
     * @param lastName applicants last name.
     * @return the return value from the applicationView method, see @applicationView.
     */
    @PostMapping
    public String search(Model model,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate created,
                         @RequestParam String firstName,
                         @RequestParam String lastName)
    {
        System.out.println("From: " + from);
        System.out.println("To " + to);
        System.out.println(Arrays.toString(selectedCompetences.toArray()));
        Set<CompetenceDTO> competences = new HashSet<>();

        for(CompetenceProfileDTO cp : selectedCompetences) {
            competences.add(cp.getCompetence());
        }

        List<ApplicationDTO> applications = listApplicationService.findApplications(
                new ApplicationSearchDTO.Builder()
                        .setAvailableFrom(DateUtil.getDateFrom(from))
                        .setAvailableTo(DateUtil.getDateFrom(to))
                        .setApplicationCreated(DateUtil.getDateFrom(created))
                        .setApplicantFirstname(firstName)
                        .setApplicantLastname(lastName)
//                        .setCompetences(competences)
                        .build()
        );
        model.addAttribute("listOfApplications", applications);
        System.out.println(applications);
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
