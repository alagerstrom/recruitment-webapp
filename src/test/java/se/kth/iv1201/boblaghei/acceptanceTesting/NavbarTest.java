package se.kth.iv1201.boblaghei.acceptanceTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NavbarTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private String base;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        this.base = "http://localhost:" + port;
        System.out.println(port);
    }

    @Test
    public void testNavbarButtons() {
        driver.get(base + "/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.id("submitLogin")).click();
        driver.findElement(By.id("homeButton")).click();
        assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/");
        driver.findElement(By.id("createApplication")).click();
        assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/apply");
        driver.findElement(By.id("viewApplications")).click();
        assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/recruiter/applications");
        driver.findElement(By.id("logout")).click();
        assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/login?logout");
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }
}
