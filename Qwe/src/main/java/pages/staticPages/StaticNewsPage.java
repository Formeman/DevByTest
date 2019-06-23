package pages.staticPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class StaticNewsPage extends BasePage {

    public StaticNewsPage (){
        super();
        PageFactory.initElements(driver,this);
    }

    private static final By headerTitles = By.xpath("//h1[@itemprop= 'headline']");
    private static final By commentsMuch = By.cssSelector("button[class *='article-stat__item']");
    private static final By comments = By.xpath("//div[@class ='comment__content-wrapper']");
    private static final By titleType = By.cssSelector("div[class = 'article-stat'] a");

    public String getHeaderTitleText(){
        if (driver.findElement(commentsMuch).getText().contentEquals("Нет комментариев")) {
            return driver.findElement(headerTitles).getText();
        }else {
            return driver.findElement(headerTitles).getText() + " " + driver.findElements(comments).size();
        }
    }

    public String getTitleType(){return driver.findElement(titleType).getText();}
}
