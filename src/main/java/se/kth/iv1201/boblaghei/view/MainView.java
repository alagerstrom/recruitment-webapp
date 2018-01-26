package se.kth.iv1201.boblaghei.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainView {

    @RequestMapping("/")
    public String mainView(){
        return "index";
    }
}
