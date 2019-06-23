package pages.staticPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class StaticEventsPage extends BasePage {

        public StaticEventsPage() {
            super();
            PageFactory.initElements(driver, this);
        }
    private static final By event = By.xpath("//a[@class='yes input']");
    private static final By popUp = By.xpath("//div[@class = 'content-alert']");

        public void popUpActivation(){driver.findElements(event).get(0).click();}

        public boolean getPopUpDisplayed(){return driver.findElement(popUp).isDisplayed();}

        public String getPopUpText(){return driver.findElement(popUp).getText();}

    }
