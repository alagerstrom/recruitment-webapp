package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.dto.UserDTO;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.service.RegisterService;

@Controller
public class RegisterView {

    @Autowired
    RegisterService registerService;

    @GetMapping("/register")
    public String getRegisterView(Model model){
        UserDTO user = new UserDTO();
        PersonDTO person = new PersonDTO();
        person.setUser(user);
        model.addAttribute("person", person);
        return "register";
    }

    @PostMapping("/register")
    public String postRegistration(@ModelAttribute PersonDTO person, Model model){
        System.out.println("I should register " + person);
        registerService.register(person);
        return "redirect:/";
    }

    @GetMapping("/listRoles")
    public String getListRolesView(Model model) {
        model.addAttribute("listOfRoles", registerService.getRolesOfLoggedInPerson());
        return "listRoles";
    }
}
