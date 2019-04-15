package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NewsPage extends BasePage {

    public NewsPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[@itemprop= 'headline']" )
    WebElement headerTitles;

    @FindBy(css = "button[class *='article-stat__item']")
    WebElement commentsMuch;

    @FindBy(xpath ="//div[@class ='comment__content-wrapper']" )
    List<WebElement> comments;

    @FindBy(css = "div[class = 'article-stat'] a")
    WebElement titleType;

    public String getHeaderTitleText(){
        if (commentsMuch.getText().contentEquals("Нет комментариев")) {
            return headerTitles.getText();
        }else {
            return headerTitles.getText() + " " + comments.size();
        }
    }

    public String getTitleType(){return titleType.getText();}
}
