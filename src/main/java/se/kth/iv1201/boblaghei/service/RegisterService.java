package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.dto.UserDTO;
import se.kth.iv1201.boblaghei.dto.UserRoleDTO;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.entity.UserRole;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import se.kth.iv1201.boblaghei.repository.UserRoleRepository;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;

import java.util.HashSet;
import java.util.Set;

/**
 * Service responsible for handling registrations.
 */
@Service
public class RegisterService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    /**
     * Registers a new person, both as a person as well as a user.
     * @param personDTO DTO containing data needed for registration.
     * @throws DuplicateUsernameException if a user is registered with an already existing username.
     */
    public void register(PersonDTO personDTO) throws DuplicateUsernameException {
        User user = createUser(personDTO.getUser());
        if(userRepository.findOne(user.getUsername()) != null) {
            throw new DuplicateUsernameException("Username is already in use, please choose another one.");
        }
        userRepository.save(user);
        personRepository.save(createPerson(personDTO));
    }

    /**
     * getLoggedInPerson() can be used to get information about the currently logged in person
     * The password in the UserDTO will be empty.
     *
     * @return The PersonDTO representing the currently logged in user, without password
     */
    public PersonDTO getLoggedInPerson() throws NoUserLoggedInException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(userDetails == null) {
            throw new NoUserLoggedInException("No user is logged in, please log in.");
        }
        User user = userRepository.findOne(userDetails.getUsername());
        Person person = personRepository.getPersonByUser(user);
        return person.getDTO();
    }

    public Set<UserRoleDTO> getRolesOfLoggedInPerson() throws NoUserLoggedInException {
        User loggedInUser = userRepository.getUserByUsername(getLoggedInPerson().getUser().getUsername());
        Set<UserRole> userRoles = userRoleRepository.getUserRolesByUser(loggedInUser);
        Set<UserRoleDTO> userRolesDTO = new HashSet<>();
        for(UserRole ur : userRoles) {
            userRolesDTO.add(ur.getDTO());
        }
        return userRolesDTO;
    }

    private User createUser(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword(), true);
    }

    private Person createPerson(PersonDTO personDTO) {
        User user = createUser(personDTO.getUser());
        return new Person(personDTO.getFirstName(), personDTO.getLastName(),
                personDTO.getPersonalNumber(), personDTO.getEmail(), user);
    }
}
