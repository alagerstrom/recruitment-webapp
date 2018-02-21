package se.kth.iv1201.boblaghei.acceptanceTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static se.kth.iv1201.boblaghei.testUtility.testUtility.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UICreateApplicationViewTest {

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
    public void testAddingCompetence() {
        driver.get(base + "/apply");
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yearsOfExperience")));
        driver.findElement(By.id("yearsOfExperience")).sendKeys("2");
        driver.findElement(By.id("addCompetenceButton")).click();
        assertThat(driver.findElement(By.id("allCompetences")).isDisplayed());
    }

    @Test
    public void testAddingAvailability() {
        driver.get(base + "/apply");
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fromDate")));

        WebElement dateWidgetFrom = driver.findElement(By.id("fromDate"));
        WebElement dateWidgetTo = driver.findElement(By.id("toDate"));

        dateWidgetFrom.sendKeys("09252017");
        dateWidgetTo.sendKeys("10102017");

        driver.findElement(By.id("addAvailabilityButton")).click();

        assertThat(driver.findElement(By.id("allAvailabilities")).isDisplayed());
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }
}
