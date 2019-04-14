
package driverSingleton;

import browser.Browser;
import factory.ChromeDriverFactory;
import factory.FireFoxFactory;
import factory.RemoteFactory;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    static WebDriverFactory factory;
    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private WebDriverSingleton() {
    }

    public static WebDriver create() {
        String browserName = System.getProperty("browserName");
        if (browserName == null) {
            browserName = "Chrome";
        }

        if (browserName.equalsIgnoreCase("FireFox")) {
            factory = new FireFoxFactory();
        } else if(browserName.equalsIgnoreCase("chrome")) {
            factory = new ChromeDriverFactory();
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        } else {
            factory = new RemoteFactory();
        }

        if (driver.get() == null) {
            driver.set(factory.create());
        }
        return new Browser(driver.get());
    }

    public static void kill() {
        if (driver.get() != null) {
            driver.get().quit();
        }
        driver.set(null);
    }
}

