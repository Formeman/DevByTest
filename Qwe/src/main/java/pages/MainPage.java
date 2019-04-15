package pages;

import browser.Browser;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends BasePage {

    public MainPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class*='header__controls'] button" )
    WebElement hamburger;

    @FindBy(css = "div[class*='Header-nav'] button" )
    WebElement mobileSearch;

    @FindBy(css = "div[class*='button_search'] button")
    WebElement desktopSearchButton;

    @FindBy(name = "q")
    WebElement searchInput;

    @FindBy(xpath = "//div[contains(@style, '949b917697')]" )
    WebElement iSoftImage;

    @FindBy(xpath = "//a[@href='//id.dev.by/@/hello']" )
    WebElement login;

    @FindBy(css = ".header__auth-image")
    WebElement authIcon;

    @FindBy(xpath = "//a[@class = 'article-preview__link article-preview__link_title']")
    List<WebElement> title;

    @FindBy(xpath =".//a[.='События']" )
    WebElement events;

    @FindBy(css = "span[class = 'article-preview__label'] a")
    List<WebElement> titleType;

    public void open(){
        driver.get("https://dev.by");
    }

    public void clickOnHamburger() {
        hamburger.click();
    }

    public void clickOnMobileSearch() {
    mobileSearch.click();
    }

    public void clickOnDesktopSearch() {
        desktopSearchButton.click();
    }

    public void goToEventsPage(){events.click();}

    public void sendSearchRequest(String searchPhrase) { searchInput.sendKeys(searchPhrase+ Keys.ENTER); }

    public void goToLoginPage(){ login.click();}

    public String search(String searchPhrase) {
        if (isMobileView()) {
            clickOnHamburger();
            clickOnMobileSearch();
        } else {
            clickOnDesktopSearch();
        }
        sendSearchRequest(searchPhrase);
        return driver.getCurrentUrl();
    }

    public void clickISoftVacancies() {
        Actions builder = new Actions(((Browser)driver).getDriver());
        builder.moveToElement(iSoftImage).moveByOffset(0, 10).click().build().perform();
    }

    public void goToISoftVacancies() {
        String currentWindow = driver.getWindowHandle();
        clickISoftVacancies();
        for (String window : driver.getWindowHandles()) {
            if (!currentWindow.contentEquals(window)) {
                driver.switchTo().window(window);
            }
        }
    }

    public boolean isOpened() {
        return driver.getCurrentUrl().contentEquals("http://dev.by");
    }

    public boolean isAutentificated(){
        try {
            return authIcon.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public String getTitleText(){
        return title.get(0).getText();
    }

    public void goToNewsPage(){title.get(0).click();}

    public String getTitleType(){return titleType.get(0).getText();}
}
