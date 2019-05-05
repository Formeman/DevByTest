import Pages.LoginPage;
import Pages.MainPage;
import Pages.VacancyPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideDevByPageObject {
    LoginPage loginPage ;
    MainPage mainPage;
    VacancyPage vacancyPage;

    @BeforeMethod
    public void start(){
        Configuration.browser = "chrome";
        Configuration.startMaximized  = true;
        Configuration.timeout = 10;
        Configuration.screenshots = false;

        loginPage = new LoginPage();
    }
    @Test
    public void desktopSearchTest(){
        String login = "at/1@virtual-email.com";
        String password = "at/1@virtual-email.com";
        open("https://id.dev.by/@/hello");

        loginPage.inputLogin(login);

        $(By.name("email")).sendKeys("at/1@virtual-email.com");
        $(By.name("password")).shouldBe(Condition.enabled).setValue("at/1@virtual-email.com");
        $(By.xpath("//button[@type = 'submit']")).click();

        $(".header__auth-image").waitUntil(Condition.visible, 10000).shouldBe(Condition.visible);
    }

    @Test
    public  void headerTest(){
        open("https://dev.by/");
        $(".header").$$(".header-nav__item")
                .filter(Condition.not(text("a")))
                .shouldHave(CollectionCondition.exactTexts("ВАКАНСИИ"));
    }

    @Test
    public void search(){
        open("https://dev.by/");

        $("div[class*='button_search'] button").click();
        $(By.name("q")).sendKeys("Comaqa"+ Keys.ENTER);

        String currentURL = WebDriverRunner.url();
        Assert.assertTrue(currentURL.toLowerCase().contains("comaqa"), "URL should contain 'Comaqa'");
    }

    @Test()
    public void iSoftTest() {
        open("https://dev.by/");

        Actions builder = new Actions(WebDriverRunner.getWebDriver());
        builder.moveToElement($(By.xpath("//div[contains(@style, '949b917697')]"))).moveByOffset(0, 10).click().build().perform();


       // $$(By.xpath("//div[@class = 'header-global__header-nav-content']")).findBy(text("Вакансии"))
                ;
            Boolean isVacancyActive = $$(By.xpath("//div[@class = 'header-global__header-nav-content']")).findBy(text("Вакансии"))
                    //$(By.xpath("//div[@class = 'header-global__header-nav-content']")).$(By.xpath(".//a[.='Вакансии']"))
                .getAttribute("class").endsWith("current");
        String salaryColour = $(By.xpath(".//a[.='" + "Зарплаты" + "']")).getCssValue("color");
        String vacancyColour = $(By.xpath(".//a[.='" + "Вакансии" + "']")).getCssValue("color");

        Assert.assertTrue(isVacancyActive, "Vacancy class should be active");
        Assert.assertNotEquals(salaryColour, vacancyColour, "Colours should be different");
    }

    @Test(dataProvider = "getCredentials", groups = {"Login"})
    public void negativeLogin_DataProvider(String login, String password) {
        String message = "Неверный логин или пароль.";
        String messageByMaster = "Error message should be 'Неверный логин или пароль.'";

        open("https://id.dev.by/@/hello");
        $(By.name("email")).sendKeys(login);
        $(By.name("password")).shouldBe(Condition.enabled).setValue(password);
        $(By.xpath("//button[@type = 'submit']")).click();
        $(".message_error").waitUntil(Condition.visible,10000).shouldHave(text(message));
    }

    @DataProvider(parallel = false)
    public Object[][] getCredentials() {
        return new String[][]{
                {"SomeLogin", "SomePassword"},
                {"Логин", "Пароль"},
                {"1", " "},
                {"", ""},
                {" ", " "},
                {"", ""},
        };
    }
}

