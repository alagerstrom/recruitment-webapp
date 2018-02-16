package se.kth.iv1201.boblaghei.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.boblaghei.dto.LoginRequest;
import se.kth.iv1201.boblaghei.dto.LoginResponse;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.util.HttpPath;
import se.kth.iv1201.boblaghei.service.RegisterService;
import se.kth.iv1201.boblaghei.service.SecurityService;

@RestController
@RequestMapping(HttpPath.USERS_PATH)
public class UserResource {

    @Autowired
    RegisterService registerService;

    @Autowired
    SecurityService securityService;

    @PostMapping(HttpPath.REGISTER_PATH)
    public void register(@RequestBody Person person) throws DuplicateUsernameException {
        registerService.register(person);
    }

    @PostMapping(HttpPath.LOGIN_PATH)
    public LoginResponse login(@RequestBody User user){
        System.out.println(user);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(user.getUsername());
        loginRequest.setPassword(user.getPassword());
        return securityService.login(loginRequest);
    }
}
