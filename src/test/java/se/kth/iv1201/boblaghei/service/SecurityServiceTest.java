package se.kth.iv1201.boblaghei.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import se.kth.iv1201.boblaghei.service.SecurityService;
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
}
