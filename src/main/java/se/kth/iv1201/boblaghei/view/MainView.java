package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.service.SecurityService;

@Controller
public class MainView {

    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public String getMainView(Model model){
        PersonDTO personDTO = securityService.getLoggedInPerson();
        model.addAttribute(personDTO);
        return "index";
    }
}
