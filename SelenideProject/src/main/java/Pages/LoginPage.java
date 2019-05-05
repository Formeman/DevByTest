package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage {
    @FindBy(name = "email" )
    WebElement loginInput;
   // SelenideElement loginIput = ;
    @FindBy(name = "password" )
    WebElement passwordInput;
    @FindBy(xpath = "//button[@type = 'submit']" )
    WebElement loginButton;
    @FindBy(css = ".message_error" )
    List<WebElement> errorMessage;

    public void clickToLoginButton(){
        loginButton.click();
    }

    public void inputLogin(String login){
        loginInput.sendKeys(login);
    }

    public void inputPassword(String password){
        passwordInput.sendKeys(password);
    }

    public int getPositiveLoginMessage(){
        return errorMessage.size();
    }

    public String getNegativeLoginMessage(){return errorMessage.get(0).getText();}

}
