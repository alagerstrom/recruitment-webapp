package se.kth.iv1201.boblaghei.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.boblaghei.dto.PersonDto;
import se.kth.iv1201.boblaghei.dto.UserDto;

@Controller
public class RegisterView {

    @GetMapping("/register")
    public String getRegisterView(Model model){
        UserDto user = new UserDto();
        PersonDto person = new PersonDto();
        person.setUser(user);
        model.addAttribute("person", person);
        return "register";
    }

    @PostMapping("/register")
    public String postRegistration(@ModelAttribute PersonDto person, Model model){
        System.out.println("I should register " + person);
        return "index";

    }
}
