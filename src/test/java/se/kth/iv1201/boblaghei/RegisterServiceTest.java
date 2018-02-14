package se.kth.iv1201.boblaghei;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import se.kth.iv1201.boblaghei.service.RegisterService;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.service.SecurityService;

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
        User firstUser = new User("kalle", "kula", true);
        Person firstRegistrant =
                new Person("kalle", "kula",
                        "12345", "e@e.e", firstUser
                );
        User secondUser = new User("kalle", "kula", true);
        Person secondRegistrant =
                new Person("hubert", "Nilsson",
                        "12345", "e@e.e", secondUser
                );
        registerService.register(firstRegistrant);
        registerService.register(secondRegistrant);
    }

    @Test
    public void testRegistrationSuccessful() throws DuplicateUsernameException {
        User firstUser = new User("maja", "skunk", true);
        Person firstRegistrant =
                new Person("kalle", "kula",
                        "12345", "e@e.e", firstUser
                );
        registerService.register(firstRegistrant);
        assertThat(userRepository.getUserByUsername(firstRegistrant.getUser().getUsername()).getUsername())
                .isEqualTo(firstUser.getUsername());
//        assertThat(userRepository.getUserByUsername(firstRegistrant.getUser().getUsername()))
    }
}
