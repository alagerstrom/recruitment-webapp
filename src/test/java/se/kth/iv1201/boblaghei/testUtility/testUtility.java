package se.kth.iv1201.boblaghei.testUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testUtility {

    public static WebDriver setupDriver() {
        String osName = System.getProperty("os.name");
        if(osName.equals("Linux")) {
            System.setProperty("webdriver.chrome.driver", "chromedriverLinux");
        } else {
            System.setProperty("webdriver.chrome.driver", "chromedriverMac");
        }
        return new ChromeDriver();
    }

    public static String setupBaseURI(int port) {
        return "http://localhost:" + port;
    }

    public static void loginRecruiter(WebDriver driver, String baseURI) {
        driver.get(baseURI + "/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.id("submitLogin")).click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));


    }

    public static void loginApplicant(WebDriver driver, String baseURI) {
        driver.get(baseURI + "/login");
        driver.findElement(By.id("username")).sendKeys("kalle");
        driver.findElement(By.id("password")).sendKeys("kula");
        driver.findElement(By.id("submitLogin")).click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yearsOfExperience")));
    }

    public static void loginFaultyCredentials(WebDriver driver, String baseURI) {
        driver.get(baseURI + "/login");
        driver.findElement(By.id("username")).sendKeys("thisUNdoesnotexist");
        driver.findElement(By.id("password")).sendKeys("thisPWdoesnotexist");
        driver.findElement(By.id("submitLogin")).click();
    }

}
