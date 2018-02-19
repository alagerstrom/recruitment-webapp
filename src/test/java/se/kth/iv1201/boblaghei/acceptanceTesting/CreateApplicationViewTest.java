package se.kth.iv1201.boblaghei.acceptanceTesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static se.kth.iv1201.boblaghei.testUtility.testUtility.loginAdmin;
import static se.kth.iv1201.boblaghei.testUtility.testUtility.setupBaseURI;
import static se.kth.iv1201.boblaghei.testUtility.testUtility.setupDriver;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateApplicationViewTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private String base;

    @Before
    public void setup() {
        driver = setupDriver();
        base = setupBaseURI(port);
        loginAdmin(driver, base);
    }

    @Test
    public void testAddingCompetence() {
        driver.findElement(By.id("yearsOfExperience")).sendKeys("2");
        driver.findElement(By.id("addCompetenceButton")).click();
        assertThat(driver.findElement(By.id("allCompetences")).isDisplayed());
    }

    @Test
    public void testAddingAvailability() {
        WebElement dateWidgetFrom = driver.findElement(By.id("fromDate"));
        WebElement dateWidgetTo = driver.findElement(By.id("toDate"));

        dateWidgetFrom.sendKeys("09252017");
        dateWidgetTo.sendKeys("10102017");

        driver.findElement(By.id("addAvailabilityButton")).click();

        assertThat(driver.findElement(By.id("allAvailabilities")).isDisplayed());
    }
}
