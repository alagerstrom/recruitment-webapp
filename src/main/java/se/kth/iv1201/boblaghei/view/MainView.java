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

    @GetMapping("/")
    public String getMainView(Model model) throws NoUserLoggedInException {
        PersonDTO personDTO = registerService.getLoggedInPerson();
        model.addAttribute(personDTO);
        return "index";
    }
}
