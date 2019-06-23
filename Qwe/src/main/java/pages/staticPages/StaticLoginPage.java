package pages.staticPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class StaticLoginPage extends BasePage {

    public StaticLoginPage(){
        super();
        PageFactory.initElements(driver, this);
    }
    private static final By loginInput = By.name("email");
    private static final By passwordInput = By.name("password");
    private static final By loginButton = By.xpath("//button[@type = 'submit']");
    private static final By errorMessage = By.cssSelector(".message_error");


    public void clickToLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void inputLogin(String login){ driver.findElement(loginInput).sendKeys(login); }

    public void inputPassword(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }

    public int getPositiveLoginMessage(){
        return driver.findElements(errorMessage).size();
    }

    public String getNegativeLoginMessage(){return driver.findElements(errorMessage).get(0).getText();}
}
