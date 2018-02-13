package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.repository.UserRepository;

/**
 * Service containing business logic needed for utilizing Spring Security.
 */
@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;


    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
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

}

