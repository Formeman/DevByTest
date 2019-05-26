import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SelenideDevByTest {
    @BeforeMethod
    public void start(){
        Configuration.browser = "chrome";
        Configuration.startMaximized  = true;
        Configuration.timeout = 10;
        Configuration.screenshots = false;
    }
    @Test
    public void desktopSearchTest(){
        open("https://id.dev.by/@/hello");

        $(By.name("email")).sendKeys("antoxa.trigubovich@mail.ru");
        $(By.name("password")).shouldBe(Condition.enabled).setValue("123qweasdzxc");
        $(By.xpath("//button[@type = 'submit']")).click();

        $(".header__auth-image").waitUntil(Condition.visible, 10000).shouldBe(Condition.visible);
    }

    @Test
    public  void headerTest(){
        open("https://dev.by/");

        $$(".header-nav__item")
                .filter(Condition.have(Condition.text("в")))
                .shouldHave(CollectionCondition.exactTexts("ВАКАНСИИ"));
    }

    @Test
    public void search(){
        open("https://dev.by/");

        $("div[class*='button_search'] button").click();
        $(By.name("q")).sendKeys("Comaqa"+ Keys.ENTER);

        Assert.assertTrue(url().toLowerCase().contains("comaqa"), "URL should contain 'Comaqa'");
    }

    @Test()
    public void iSoftTest() {
        open("https://dev.by/");

        String currentWindow = WebDriverRunner.getWebDriver().getWindowHandle();

        Actions builder = new Actions(WebDriverRunner.getWebDriver());
        builder.moveToElement($(By.xpath("//div[contains(@style, '949b917697')]"))).moveByOffset(0, 10).click().build().perform();

        for (String window : WebDriverRunner.getWebDriver().getWindowHandles()) {
            if (!currentWindow.contentEquals(window)) {
                WebDriverRunner.getWebDriver().switchTo().window(window);
            }
        }

        Boolean isVacancyActive = $(By.xpath("//div[@class = 'header-global__general']")).$(By.xpath(".//a[.='" + "Вакансии" + "']"))
                .getAttribute("class").endsWith("current");
        String salaryColour = $(By.xpath("//div[@class = 'header-global__general']")).$(By.xpath(".//a[.='" + "Зарплаты" + "']")).getCssValue("color");
        String vacancyColour = $(By.xpath("//div[@class = 'header-global__general']")).$(By.xpath(".//a[.='" + "Вакансии" + "']")).getCssValue("color");

        Assert.assertTrue(isVacancyActive, "Vacancy class should be active");
        Assert.assertNotEquals(salaryColour, vacancyColour, "Colours should be different");
    }

    @Test(dataProvider = "getCredentials", groups = {"Login"})
    public void negativeLogin_DataProvider(String login, String password, String expectedErrorMessage) {
        close();
        open("https://id.dev.by/@/hello");

        $(By.name("email")).sendKeys(login);
        $(By.name("password")).shouldBe(Condition.enabled).setValue(password);
        $(By.xpath("//button[@type = 'submit']")).click();
        $(".message_error").waitUntil(Condition.visible,10000).should(Condition.text(expectedErrorMessage));
    }

    @DataProvider(parallel = false)
    public Object[][] getCredentials() {
        return new String[][]{
                {"SomeLogin", "SomePassword", "Неверный логин или пароль."},
                {"Логин", "Пароль", "Неверный логин или пароль."},
                {"1", " ", "Неверный логин или пароль."},
                {"", "", "Введите адрес электронной почты или имя пользователя."},
                {" ", " ", "Неверный логин или пароль."},
                {"", "", "Введите адрес электронной почты или имя пользователя."},
        };
    }

    @Test()
    public void tagTest(){
        String headerTitleText;
        String titleText;
        WebElement title = $(By.xpath("//a[@class = 'article-preview__link article-preview__link_title']"));
        WebElement headerTitle = $(By.xpath("//h1[@itemprop= 'headline']"));

        open("https://dev.by/");
        titleText = title.getText();;
        title.click();
        if ($(("button[class *='article-stat__item']")).getText().contentEquals("Нет комментариев")) {
            headerTitleText = headerTitle.getText();
        }else {
            headerTitleText = headerTitle.getText() + " " + $(By.xpath("//div[@class ='comment__content-wrapper']")).getSize();
        }

        Assert.assertEquals(headerTitleText,titleText,"Title and header should be the same");
    }

    @Test
    public void popUpCheck(){
        open("https://dev.by/");
        $(By.xpath(".//a[.='События']")).click();
        $(("a[class='yes input']")).click();

        WebElement popUpActivation = $(By.xpath("//div[@class = 'content-alert']")).waitUntil(Condition.visible,5000);

        Assert.assertTrue(popUpActivation.isDisplayed(),"Pop up should be displayed");
        Assert.assertEquals(popUpActivation.getText(),"Иду! =)", "Message in pop up should be");
    }

}
