package se.kth.iv1201.boblaghei.service;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.dto.UserDTO;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.Role;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import se.kth.iv1201.boblaghei.util.logger.SecurityLogger;

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
     * @param personDTO DTO containing data needed for registration.
     * @throws DuplicateUsernameException if a user is registered with an already existing username.
     */
    public void register(PersonDTO personDTO) throws DuplicateUsernameException {
        if (userRepository.findOne(personDTO.getUser().getUsername()) != null) {
            securityLogger.log("Tried registering the username " + personDTO.getUser().getUsername() + " that is already in use.");
            throw new DuplicateUsernameException("Username is already in use, please choose another one.");
        }

        Person person = createPerson(personDTO);
        personRepository.save(person);
    }

    /**
     * getLoggedInPerson() is used to get information about the currently logged in person
     * The password in the UserDTO will be empty.
     *
     * @return The PersonDTO representing the currently logged in user, without password
     */
    public Person getLoggedInPerson() throws NoUserLoggedInException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getPerson();
        }
        throw new NoUserLoggedInException("User is not logged in");
    }

    private Person createPerson(PersonDTO personDTO) {
        User user = createUser(personDTO.getUser());
        return new Person(personDTO.getFirstName(), personDTO.getLastName(),
                personDTO.getPersonalNumber(), personDTO.getEmail(), user);
    }

    private User createUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), true);
        user.getRoles().add(Role.ROLE_APPLICANT);
        return user;
    }
}
