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

/**
 * UserResource
 *
 * Defines a REST resource, that makes it possible to register users, and to log in.
 *
 * To register a user, send POST to /api/users/register.
 * If successful, the response will contain a valid JWT token.
 *
 * To login, send POST to /api/users/login.
 * If successful, the response will contain a valid JWT token.
 */
@RestController
@RequestMapping(HttpPath.USERS_PATH)
public class UserResource {

    @Autowired
    SecurityService securityService;

    /**
     * Defines the mapping of POST requests sent to /api/users/register
     *
     * @param person The person to register
     * @return A LoginResponse containing a JWT token, and the registered Person
     *
     * @throws DuplicateUsernameException If the username already exists
     */
    @PostMapping(HttpPath.REGISTER_PATH)
    public LoginResponse register(@RequestBody @Valid Person person) throws DuplicateUsernameException {
        return securityService.register(person);
    }

    /**
     * Defines the mapping of POST requests sent to /api/users/login
     *
     * @param request A LoginRequest containing username and password
     *
     * @return A LoginResponse containing a JWT token and the logged in Person
     *
     * @throws LoginException if the login failed
     */
    @PostMapping(HttpPath.LOGIN_PATH)
    public LoginResponse login(@RequestBody @Valid LoginRequest request) throws LoginException {
        LoginResponse loginResponse = securityService.login(request);
        return loginResponse;
    }
}
