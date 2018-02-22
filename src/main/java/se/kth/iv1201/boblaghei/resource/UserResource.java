package se.kth.iv1201.boblaghei.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.boblaghei.dto.LoginRequest;
import se.kth.iv1201.boblaghei.dto.LoginResponse;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.exception.LoginException;
import se.kth.iv1201.boblaghei.util.HttpPath;
import se.kth.iv1201.boblaghei.service.SecurityService;

import javax.validation.Valid;

@RestController
@RequestMapping(HttpPath.USERS_PATH)
public class UserResource {

    @Autowired
    SecurityService securityService;

    @PostMapping(HttpPath.REGISTER_PATH)
    public LoginResponse register(@RequestBody @Valid Person person) throws DuplicateUsernameException {
        return securityService.register(person);
    }

    @PostMapping(HttpPath.LOGIN_PATH)
    public LoginResponse login(@RequestBody @Valid LoginRequest request) throws LoginException {
        LoginResponse loginResponse = securityService.login(request);
        return loginResponse;
    }
}