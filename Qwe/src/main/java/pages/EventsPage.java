package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EventsPage extends BasePage {
    public EventsPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='yes input']")
    List<WebElement> event;

    @FindBy(xpath = "//div[@class = 'content-alert']")
    WebElement popUp;

    public void popUpActivation(){event.get(0).click();}

    public boolean getPopUpDisplayed(){return popUp.isDisplayed();}

    public String getPopUpText(){return popUp.getText();}

}
