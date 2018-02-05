package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.service.RegisterService;
import se.kth.iv1201.boblaghei.service.SecurityService;

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
