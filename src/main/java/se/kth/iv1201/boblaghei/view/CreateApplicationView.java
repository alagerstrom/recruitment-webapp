package se.kth.iv1201.boblaghei.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.boblaghei.entity.Availability;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;

import java.util.HashSet;
import java.util.Set;

/**
 * Controller responsible for providing mappings used for creating applications.
 */
@Controller
@RequestMapping("/apply")
public class CreateApplicationView extends AbstractApplicationView {

    private Set<Availability> availabilities = new HashSet<>();

    /**
     * Invocated when a GET-request is sent to "/apply". Loads the resources needed for creating an application.
     *
     * @param model responsible for making data available in the view
     * @return the apply.html page
     */
    @Override
    @GetMapping
    public String applicationView(Model model) {
        availableCompetences = createApplicationService.listAllCompetences();
        model.addAttribute("availabilities", availabilities);
        model.addAttribute("availableCompetences", availableCompetences);
        model.addAttribute("selectedCompetences", selectedCompetences);
        return "apply";
    }

    /**
     * Invocated when a POST-request is sent to "/add_availability". Creates a new <code>AvailabilityDTO</code> that is
     * added to the list availabilities.
     *
     * @param model responsible for making data available in the view
     * @param availability entity that represents when the applicant is available
     * @return return-value of applicationView method, see @applicationView
     */
    @PostMapping("/add-availability")
    public String addAvailability(Model model, @ModelAttribute Availability availability) {
        availabilities.add(availability);
        return applicationView(model);
    }

    /**
     * Invocated when a POST-request is sent to "/submit-application". Creates an application based on the data the user
     * has added and saves this application to the database by using <code>CreateApplicationService</code>
     *
     * @param model responsible for making data available in the view
     * @return redirects the user to the index.html page
     */
    @PostMapping("/submit-application")
    public String submitApplication(Model model) {
//        try {
//            createApplicationService.createApplicationForCurrentUser(selectedCompetences, availabilities);
//        } catch (NoUserLoggedInException e) {
//            errorLogger.log(e.getMessage());
//            e.printStackTrace();
//        }
        return "redirect:/";
    }
}
