package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.dto.UserDTO;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.service.RegisterService;
import se.kth.iv1201.boblaghei.util.logger.ErrorLogger;

/**
 * Controller responsible for providing mappings used for registration of a new user.
 */
@Controller
public class RegisterView {

    @Autowired
    RegisterService registerService;

    @Autowired
    ErrorLogger errorLogger;

    /**
     * Invocated on a GET-request to "/register". Loads the register view which enables a person to register on the website.
     * @param model responsible for making data available in the view
     * @return the register.html page
     */
    @GetMapping("/register")
    public String getRegisterView(Model model){
        UserDTO user = new UserDTO();
        PersonDTO person = new PersonDTO();
        person.setUser(user);
        model.addAttribute("person", person);
        return "register";
    }

    /**
     * Invocated on a POST-request to "/register". Tries to register the entered data
     * @param person contains the data to be registered
     * @param model responsible for making data available in the view
     * @return the index.html page
     */
    @PostMapping("/register")
    public String postRegistration(@ModelAttribute PersonDTO person, Model model){
        System.out.println("I should register " + person);
        try{
            registerService.register(person);
        } catch (DuplicateKeyException | DuplicateUsernameException e) {
            errorLogger.log(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
