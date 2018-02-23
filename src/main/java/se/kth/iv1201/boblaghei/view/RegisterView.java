package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.service.SecurityService;
import se.kth.iv1201.boblaghei.util.logger.ErrorLogger;

import javax.validation.Valid;

/**
 * Controller responsible for providing mappings used for registration of a new user.
 */
@Controller
public class RegisterView {

    @Autowired
    SecurityService securityService;

    /**
     * Invocated on a GET-request to "/register". Loads the register view which enables a person to register on the website.
     *
     * @param model responsible for making data available in the view
     * @return the register.html page
     */
    @GetMapping("/register")
    public String getRegisterView(Model model) {
        User user = new User();
        Person person = new Person();
        person.setUser(user);
        model.addAttribute("person", person);
        return "register";
    }
}
