package se.kth.iv1201.boblaghei.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.rest.util.HttpPath;
import se.kth.iv1201.boblaghei.service.RegisterService;

@RestController
@RequestMapping(HttpPath.USERS_PATH)
public class UserResource {

    @Autowired
    RegisterService registerService;

    @PostMapping(HttpPath.REGISTER_PATH)
    public void register(@RequestBody Person person) throws DuplicateUsernameException {
        registerService.register(person);
    }

    @PostMapping(HttpPath.LOGIN_PATH)
    public void login(@RequestBody User user){
        System.out.println("Logging in as " + user);
    }
}
