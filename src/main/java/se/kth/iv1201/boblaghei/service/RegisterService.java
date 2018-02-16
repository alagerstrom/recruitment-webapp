package se.kth.iv1201.boblaghei.service;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.Role;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import se.kth.iv1201.boblaghei.util.logger.SecurityLogger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Service containing business logic for handling registrations.
 */
@Slf4j
@Service
public class RegisterService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    SecurityLogger securityLogger;


    /**
     * Registers a new person, both as a person as well as a user.
     *
     * @param person entity containing data needed for registration.
     * @throws DuplicateUsernameException if a user is registered with an already existing username.
     */
    public void register(Person person) throws DuplicateUsernameException {
        if (userRepository.findOne(person.getUser().getUsername()) != null) {
            securityLogger.log("Tried registering the username " + person.getUser().getUsername() + " that is already in use.");
            throw new DuplicateUsernameException("Username is already in use, please choose another one.");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_APPLICANT);
        person.getUser().setRoles(roles);
        person.getUser().setEnabled(true);
        personRepository.save(person);
    }
}
