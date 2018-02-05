package se.kth.iv1201.boblaghei;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.dto.UserDTO;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import se.kth.iv1201.boblaghei.service.RegisterService;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterServiceTest {

    @Autowired
    RegisterService registerService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;

    @Test(expected = DuplicateUsernameException.class)
    public void testDuplicateUsername() throws DuplicateUsernameException {
        UserDTO firstUser = new UserDTO("kalle", "kula", true);
        PersonDTO firstRegistrant =
                new PersonDTO("kalle", "kula",
                        "12345", "e@e.e", firstUser
                );
        UserDTO secondUser = new UserDTO("kalle", "kula", true);
        PersonDTO secondRegistrant =
                new PersonDTO("hubert", "Nilsson",
                        "12345", "e@e.e", secondUser
                );
        registerService.register(firstRegistrant);
         registerService.register(secondRegistrant);
    }

    @Test
    public void testRegistrationSuccessful() throws DuplicateUsernameException {
        UserDTO firstUser = new UserDTO("maja", "skunk", true);
        PersonDTO firstRegistrant =
                new PersonDTO("kalle", "kula",
                        "12345", "e@e.e", firstUser
                );
        registerService.register(firstRegistrant);
        assertThat(userRepository.getUserByUsername(firstRegistrant.getUser().getUsername()).getUsername())
                .isEqualTo(firstUser.getUsername());
        assertThat(personRepository.getPersonByUser(firstUser.getEntity()).getFirstName()).isEqualTo(firstRegistrant.getFirstName());
    }

    @Test(expected = NoUserLoggedInException.class)
    public void testGettingUserWithNoneLoggedIn() throws NoUserLoggedInException {
        registerService.getLoggedInPerson();
    }

}
