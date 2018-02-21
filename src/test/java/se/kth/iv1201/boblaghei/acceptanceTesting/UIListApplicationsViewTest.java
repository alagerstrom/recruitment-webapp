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

import static org.assertj.core.api.Java6Assertions.assertThat;
import static se.kth.iv1201.boblaghei.testUtility.testUtility.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UIListApplicationsViewTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private String base;

    @Before
    public void setup() {
        driver = setupDriver();
        base = setupBaseURI(port);
        loginRecruiter(driver, base);
    }

    @Test
    public void fullSearchTest() {
        driver.get(base + "/recruiter/applications");
        try {
            driver.findElement(By.id("search")).click();
        } catch(org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.id("search")).click();
        }
        assertThat(driver.findElement(By.id("searchResultTable")).isDisplayed());
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }
}
