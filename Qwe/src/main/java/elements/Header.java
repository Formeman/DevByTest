package elements;

import driverSingleton.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private By selfWebElement;
    private WebDriver driver = WebDriverSingleton.create();

    public Header(By selfWebElement) {
        this.selfWebElement = selfWebElement;
    }

    public String getColour(String menuItemName) {
        return driver.findElement(selfWebElement)
                .findElement(By.xpath(".//a[.='" + menuItemName + "']"))
                .getCssValue("color");
    }

    public Boolean isItemActive(String menuItemName) {
        return driver.findElement(selfWebElement)
                .findElement(By.xpath(".//a[.='" + menuItemName + "']"))
                .findElement(By.xpath(".."))
                .getAttribute("class")
                .endsWith("active");
    }


}
