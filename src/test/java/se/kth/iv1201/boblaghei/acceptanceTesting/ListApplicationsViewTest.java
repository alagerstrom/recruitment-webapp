package se.kth.iv1201.boblaghei.acceptanceTesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static se.kth.iv1201.boblaghei.testUtility.testUtility.loginAdmin;
import static se.kth.iv1201.boblaghei.testUtility.testUtility.setupBaseURI;
import static se.kth.iv1201.boblaghei.testUtility.testUtility.setupDriver;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListApplicationsViewTest {

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
    public void fullSearchTest() {
        driver.findElement(By.id("search")).click();
        assertThat(driver.findElement(By.id("searchResultTable")).isDisplayed());
    }
}
