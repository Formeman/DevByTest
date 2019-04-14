package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends BasePage {

    public LoginPage(){
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email" )
    WebElement loginInput;

    @FindBy(name = "password" )
    WebElement passwordInput;

    @FindBy(xpath = "//button[@type = 'submit']" )
    WebElement loginButton;

    @FindBy(css = ".message_error" )
    List <WebElement>  errorMessage;

    public void clickToLoginButton(){
        loginButton.click();
    }

    public void inputLogin(String login){ loginInput.sendKeys(login); }

    public void inputPassword(String password){
        passwordInput.sendKeys(password);
    }

    public int getPositiveLoginMessage(){
        return errorMessage.size();
    }

    public String getNegativeLoginMessage(){return errorMessage.get(0).getText();}
}
