package pages;

import driverSingleton.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver = WebDriverSingleton.create();

    public boolean isMobileView() {
        String className = driver.findElement(By.tagName("html")).getAttribute("class");
        return !className.contains("no-touchevents ");
    }
}
