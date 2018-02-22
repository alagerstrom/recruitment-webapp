package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.boblaghei.dto.LoginRequest;
import se.kth.iv1201.boblaghei.dto.LoginResponse;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.Role;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.exception.LoginException;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import se.kth.iv1201.boblaghei.security.TokenService;

import java.util.HashSet;
import java.util.Set;

/**
 * Service containing business logic needed for utilizing Spring Security.
 */
@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    /**
     * Loads a user given a username.
     * @param username the username that the user one wants to load has.
     * @return an instance of UserDetails, which contains core user information, such as username, password, roles etc.
     * @throws UsernameNotFoundException thrown if no user with that username exists.
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username);
        if (user == null)
            throw new UsernameNotFoundException("User does not exist");
        return user;
    }

    /**
     * getLoggedInPerson() is used to get information about the currently logged in person.
     * @return The Person representing the currently logged in user, without password
     * @throws NoUserLoggedInException if no user is logged on, thus no person is logged on
     */
    public Person getLoggedInPerson() throws NoUserLoggedInException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getPerson();
        }
        throw new NoUserLoggedInException("User is not logged in");
    }

    /**
     * Logs in a user, performs authentication and sets the token in the <code>LoginResponse</code>
     * @param loginRequest LoginRequest that contains username and password
     * @return Loginresponse that contains a person and a token
     */
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) throws LoginException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            User user = userRepository.getUserByUsername(loginRequest.getUsername());
            String token = tokenService.createToken(user);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setPerson(user.getPerson());
            return loginResponse;
        } catch (Exception e){
            throw new LoginException("Wrong username or password");
        }
    }

    /**
     * Registers a new person, both as a person as well as a user.
     *
     * @param person entity containing data needed for registration.
     * @throws DuplicateUsernameException if a user is registered with an already existing username.
     */
    @Transactional
    public LoginResponse register(Person person) throws DuplicateUsernameException {
        if (userRepository.findOne(person.getUser().getUsername()) != null) {
            throw new DuplicateUsernameException("Username " + person.getUser().getUsername() + " is already in use, please choose another one.");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_APPLICANT);
        person.getUser().setRoles(roles);
        person.getUser().setEnabled(true);
        person.getUser().setPerson(person);
        personRepository.save(person);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setPerson(person);
        loginResponse.setToken(tokenService.createToken(person.getUser()));
        return loginResponse;
    }

}

