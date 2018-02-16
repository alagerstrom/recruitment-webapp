package se.kth.iv1201.boblaghei.view.applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;
import se.kth.iv1201.boblaghei.util.logger.ErrorLogger;

/**
 * Controller responsible for providing mappings used for creating applications.
 */
@Controller
@RequestMapping("/apply")
public class CreateApplicationView {

    @Autowired
    private CreateApplicationService createApplicationService;

    @Autowired
    private ErrorLogger errorLogger;

    /**
     * Invocated when a GET-request is sent to "/apply". Loads the resources needed for creating an application.
     *
     * @param model responsible for making data available in the view
     * @return the apply.html page
     */
    @GetMapping
    public String applicationView(Model model) {
        model.addAttribute("competences", createApplicationService.listAllCompetences());
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

    /**
     * Invocated when a POST-request is sent to "/submit-application". Creates an application based on the data the user
     * has added and saves this application to the database by using <code>CreateApplicationService</code>
     *
     * @return redirects the user to the index.html page
     */
    @PostMapping("/submit-application")
    public String submitApplication(@RequestBody Application application) {
        try {
            createApplicationService.createApplicationForCurrentUser(application);
        } catch (NoUserLoggedInException e) {
            errorLogger.log(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
