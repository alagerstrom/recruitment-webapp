package se.kth.iv1201.boblaghei.acceptanceTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;
import se.kth.iv1201.boblaghei.exception.LoginException;

import static org.assertj.core.api.Assertions.*;
import static se.kth.iv1201.boblaghei.testUtility.testUtility.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UILoginViewTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private String base;

    @Before
    public void setup() {
        driver = setupDriver();
        base = setupBaseURI(port);
    }

    @Test
    public void testLoginRecruiter() throws InterruptedException {
        loginRecruiter(driver, base);

        assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/recruiter/applications");
    }

    @Test
    public void testLoginApplicant() {
        loginApplicant(driver, base);
        assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/apply");
    }

    @Test
    public void testLoginWrongCredentials() {
        String loginURL = base + "/login";
        loginFaultyCredentials(driver, base);
        assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(loginURL);
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }
}