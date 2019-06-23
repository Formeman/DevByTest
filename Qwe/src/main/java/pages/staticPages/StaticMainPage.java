package pages.staticPages;

import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class StaticMainPage extends BasePage {

    public StaticMainPage() {
        super();
        PageFactory.initElements(driver, this);
    }
    private static final By hamburger = By.cssSelector("div[class*='header__controls'] button");
    private static final By mobileSearch = By.cssSelector("div[class*='Header-nav'] button");
    private static final By desktopSearchButton = By.cssSelector("div[class*='button_search'] button");
    private static final By searchInput = By.name("q");
    private static final By iSoftImage = By.xpath("//div[contains(@style, '949b917697')]");
    private static final By login = By.xpath("//a[@href='//id.dev.by/@/hello']");
    private static final By authIcon = By.cssSelector(".header__auth-image");
    private static final By title = By.xpath("//a[@class = 'article-preview__link article-preview__link_title']");
    private static final By events = By.xpath(".//a[.='События']");
    private static final By titleType = By.cssSelector("span[class = 'article-preview__label'] a");

    public void open(){
        driver.get("https://dev.by");
    }

    public void clickOnHamburger() {
        driver.findElement(hamburger).click();
    }

    public void clickOnMobileSearch() {
        driver.findElement(mobileSearch).click();
    }

    public void clickOnDesktopSearch() {
        driver.findElement(desktopSearchButton).click();
    }

    public void goToEventsPage(){driver.findElement(events).click();}

    public void sendSearchRequest(String searchPhrase) { driver.findElement(searchInput).sendKeys(searchPhrase+ Keys.ENTER); }

    public void goToLoginPage(){ driver.findElement(login).click();}

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
        builder.moveToElement(driver.findElement(iSoftImage)).moveByOffset(0, 10).click().build().perform();
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
            return driver.findElement(authIcon).isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public String getTitleText(){
        return driver.findElements(title).get(0).getText();
    }

    public void goToNewsPage(){driver.findElements(title).get(0).click();}

    public String getTitleType(){return driver.findElements(titleType).get(0).getText();}
}
