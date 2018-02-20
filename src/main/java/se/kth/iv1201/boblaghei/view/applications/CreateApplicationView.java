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
}
