package se.kth.iv1201.boblaghei.testUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    public static void loginAdmin(WebDriver driver, String baseURI) {
        driver.get(baseURI + "/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.id("submitLogin")).click();
    }

    public static void loginFaultyCredentials(WebDriver driver, String baseURI) {
        driver.get(baseURI + "/login");
        driver.findElement(By.id("username")).sendKeys("thisUNdoesnotexist");
        driver.findElement(By.id("password")).sendKeys("thisPWdoesnotexist");
        driver.findElement(By.id("submitLogin")).click();

    }
}
