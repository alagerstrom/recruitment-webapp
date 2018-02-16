package se.kth.iv1201.boblaghei.testUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class driverSetup {

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
}
