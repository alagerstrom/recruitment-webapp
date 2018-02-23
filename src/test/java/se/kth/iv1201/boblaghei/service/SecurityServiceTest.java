package se.kth.iv1201.boblaghei.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import se.kth.iv1201.boblaghei.dto.LoginRequest;
import se.kth.iv1201.boblaghei.dto.LoginResponse;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.exception.DuplicateUsernameException;
import se.kth.iv1201.boblaghei.exception.LoginException;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityServiceTest {

    @Autowired
    SecurityService securityService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testLoadUserByUsername() {
        String testUsername = "testUN";
        User user = new User(testUsername, "testPW", true);
        userRepository.save(user);
        UserDetails userDetails = securityService.loadUserByUsername(testUsername);
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualToIgnoringCase(testUsername);

    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByNonExistingUserName() {
        String nonExistingUsername = "nonexistant";
        securityService.loadUserByUsername(nonExistingUsername);
    }

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
        securityService.register(firstRegistrant);
        securityService.register(secondRegistrant);
    }

    @Test
    public void testRegistrationSuccessful() throws DuplicateUsernameException {
        User firstUser = new User("maja", "skunk", true);
        Person firstRegistrant =
                new Person("kalle", "kula",
                        "12345", "e@e.e", firstUser
                );
        securityService.register(firstRegistrant);
        assertThat(userRepository.getUserByUsername(firstRegistrant.getUser().getUsername()).getUsername())
                .isEqualTo(firstUser.getUsername());
    }

    @Test
    public void testLogin() throws DuplicateUsernameException, LoginException {
        User user = new User("sven", "skunk", true);
        Person person =
                new Person("Sven", "Thorgren",
                        "123456789", "sven95@gmail.com", user
                );
        securityService.register(person);

        LoginRequest request = new LoginRequest();
        request.setUsername(user.getUsername());
        request.setPassword(user.getPassword());
        LoginResponse response = securityService.login(request);
        assertThat(response.getPerson().getFirstName()).isEqualToIgnoringCase(person.getFirstName());
        assertThat(response.getPerson().getPersonalNumber()).isEqualToIgnoringCase(person.getPersonalNumber());
        assertThat(response.getToken()).isNotNull();
    }

    @Test(expected = LoginException.class)
    public void testFailedLogin() throws LoginException {
        LoginRequest request = new LoginRequest();
        request.setUsername("nonexistant");
        request.setPassword("nonexistant");
        securityService.login(request);
    }
}
