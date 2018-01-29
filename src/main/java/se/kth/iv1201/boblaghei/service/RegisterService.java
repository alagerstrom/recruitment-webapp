package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.dto.UserDTO;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.repository.UserRepository;

/**
 * Service responsible for handling registrations.
 */
@Service
public class RegisterService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;

    public void register(PersonDTO personDTO) /*throws RegisterException */{

    }

    private User createUser(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.isEnabled());
    }

    /*private createPerson(PersonDTO personDTO) {
        User user = createUser(personDTO.getUser());
        return new Person(personDTO.getFirstName(), personDTO.getLastName(),
                personDTO.getPersonalNumber(), personDTO.getEmail(), );
    }*/
}
