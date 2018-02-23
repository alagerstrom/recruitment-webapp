package se.kth.iv1201.boblaghei.acceptanceTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static se.kth.iv1201.boblaghei.testUtility.testUtility.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UINavbarTest {

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
    public void testNavBarHomeButtonRecruiter() {
        loginRecruiter(driver, base);
        try {
            driver.findElement(By.id("homeButton")).click();
        } catch(org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("homeButton")).click();
        }
        assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/recruiter/applications");
    }

    @Test
    public void testNavBarCreateAppButton() {
        loginRecruiter(driver, base);
        try {
            driver.findElement(By.id("createApplication")).click();
        } catch(org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("createApplication")).click();
        }
            assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/apply");
    }

    @Test
    public void testNavBarViewAppsButton() {
        loginRecruiter(driver, base);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewApplications")));
        try {
            driver.findElement(By.id("viewApplications")).click();
        } catch(org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("viewApplications")).click();
        }
            assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/recruiter/applications");
    }

    @Test
    public void testNavBarLogoutButton() {
        loginRecruiter(driver, base);
        try{
            driver.findElement(By.id("logout")).click();
        } catch(org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("logout")).click();
        }
            assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/login?logout");
    }

    @Test
    public void testNavBarHomeButtonApplicant() {
        loginApplicant(driver, base);
        try {
            driver.findElement(By.id("homeButton")).click();
        } catch(org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("homeButton")).click();
        }
        assertThat(driver.getCurrentUrl()).isEqualToIgnoringCase(base + "/apply");
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }
}
