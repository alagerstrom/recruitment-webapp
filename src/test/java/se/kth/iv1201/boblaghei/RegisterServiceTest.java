package se.kth.iv1201.boblaghei;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.dto.UserDTO;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.service.RegisterService;
import se.kth.iv1201.boblaghei.util.exception.DuplicateUsernameException;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterServiceTest {

    @Autowired
    RegisterService registerService;

    @Autowired
    PersonRepository personRepository;

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
    public void testRegistrationSuccessfull() throws DuplicateUsernameException {
        UserDTO firstUser = new UserDTO("maja", "skunk", true);
        PersonDTO firstRegistrant =
                new PersonDTO("kalle", "kula",
                        "12345", "e@e.e", firstUser
                );
        registerService.register(firstRegistrant);
        assertThat(personRepository.getPersonByUser(
                new User(firstUser.getUsername(),
                firstUser.getPassword(), firstUser.isEnabled())).getDTO()).isEqualTo(firstUser);
    }

    @Test(expected = NullPointerException.class)
    public void testGettingUserWithNoneLoggedIn() {
        registerService.getLoggedInPerson();
    }

}
