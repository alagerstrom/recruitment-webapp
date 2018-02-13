package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.service.RegisterService;

/**
 * Controller responsible for providing mappings used for initial opening of application.
 */
@Controller
public class MainView {

    @Autowired
    RegisterService registerService;

    /**
     * Invocated on a GET-request to "/". Loads a welcome message for the currently logged on user.
     * @param model responsible for making data available in the view
     * @return the index.html page
     */
    @GetMapping("/")
    public String getWelcomeView(Model model) {

        PersonDTO personDTO = null;
        try {
            personDTO = registerService.getLoggedInPerson();
            model.addAttribute(personDTO);
        } catch (NoUserLoggedInException ignored) {
        }
        return "index";
    }
}
