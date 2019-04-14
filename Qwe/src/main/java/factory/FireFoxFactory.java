package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxFactory extends WebDriverFactory {
    @Override
    public WebDriver create() {
        return new FirefoxDriver();
    }
}
