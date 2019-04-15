import browser.Browser;
import driverSingleton.WebDriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class DevBy {
    LoginPage loginPage ;
    MainPage mainPage;
    VacancyPage vacancyPage;
    WebDriver driver;
    NewsPage newsPage;
    EventsPage eventsPage;

    @BeforeMethod(alwaysRun = true)
    public void BeforeMethod() {
        driver = WebDriverSingleton.create();
        loginPage = new LoginPage();
        mainPage = new MainPage();
        vacancyPage = new VacancyPage();
        newsPage = new NewsPage();
        eventsPage = new EventsPage();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://dev.by/");
    }

    @AfterMethod(alwaysRun = true)
    public void finish(Method method) {
        WebDriverSingleton.kill();
    }

    @Test
    public void search(){
        String currentURL = mainPage.search("Comaqa");

        Assert.assertTrue(currentURL.toLowerCase().contains("comaqa"), "URL should contain 'Comaqa'");
    }

    @Test(groups = "iSoft")
    public void iSoftTest() {
        if (mainPage.isMobileView()) {
            throw new SkipException("This test can be preformed only for desktop view");
        }

        mainPage.goToISoftVacancies();

        takeScreenshot();

        Boolean isVacancyActive = vacancyPage.isHeaderItemActive("Вакансии");
        String salaryColour = vacancyPage.getHeaderItemColour("Зарплаты");
        String vacancyColour = vacancyPage.getHeaderItemColour("Вакансии");

        Assert.assertTrue(isVacancyActive, "Vacancy class should be active");
        Assert.assertNotEquals(salaryColour, vacancyColour, "Colours should be different");
    }

    private void takeScreenshot() {
        WebDriver ourDriver = ((Browser) driver).getDriver();

        File src= ((TakesScreenshot)ourDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("Error.png"));
        }  catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }


    @Test(groups = {"Regression", "Login"})
    public void negativeLogin() {
        String login = "SomeLogin";
        String password = "SomePassword";
        String expectedErrorMessage = "Неверный логин или пароль.";
        String messageByMaster = "Error message should be 'Неверный логин или пароль.'";

        mainPage.goToLoginPage();
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickToLoginButton();

        String negativeMessage = loginPage.getNegativeLoginMessage();
        Assert.assertEquals(negativeMessage, expectedErrorMessage, messageByMaster);
    }

    @Test(groups = {"Regression", "Login"})
    public void positiveLogin() {
        String login = "at/1@virtual-email.com";
        String password = "at/1@virtual-email.com";
        String messageByMaster = "Error message shouldn't be displayed";

        mainPage.goToLoginPage();
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getPositiveLoginMessage(),0,messageByMaster);
    }

    @Test(groups = {"Login"})
    public void emptyPassword() {
        String login = "SomeLogin";
        String expectedErrorMessage = "Введите пароль.";
        String messageByMaster = "Error message should be 'Неверный логин или пароль.'";

        mainPage.goToLoginPage();
        loginPage.inputLogin(login);
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getNegativeLoginMessage(), expectedErrorMessage, messageByMaster);
    }

    @Test(dataProvider = "getCredentials", groups = {"Login"})
    public void negativeLogin_DataProvider(String login, String password, String expectedErrorMessage) {
        String messageByMaster = "Error message should be 'Неверный логин или пароль.'";

        mainPage.goToLoginPage();
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getNegativeLoginMessage(), expectedErrorMessage, messageByMaster);
    }

    @Test(groups = {"iSoft", "Regression"})
    public void iSoftTest_softAssert() {
        mainPage.goToISoftVacancies();

        Boolean isVacancyActive = vacancyPage.isHeaderItemActive("Вакансии");
        String salaryColour = vacancyPage.getHeaderItemColour("Зарплаты");
        String vacancyColour = vacancyPage.getHeaderItemColour("Вакансии");

        SoftAssert softAsset = new SoftAssert();

        softAsset.assertTrue(isVacancyActive, "Vacancy class should be active");
        softAsset.assertNotEquals(salaryColour, vacancyColour, "Colours should be different");
        softAsset.assertAll();
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

    @Test
    public void tagTest(){
        String headerTitleText;
        String titleText;

        titleText = mainPage.getTitleText();
        System.out.println(titleText);
        mainPage.goToNewsPage();
        headerTitleText = newsPage.getHeaderTitleText();
        System.out.println(headerTitleText);

        Assert.assertEquals(headerTitleText,titleText,"Title and header should be the same");
    }

    @Test
    public void popUpCheck(){
        mainPage.goToEventsPage();
        eventsPage.popUpActivation();

        Assert.assertTrue(eventsPage.getPopUpDisplayed(),"Pop up should be displayed");
        Assert.assertEquals(eventsPage.getPopUpText(),"Иду! =)", "Message in pop up should be");
    }

    @Test
    public void typeCheck(){
       String titleType =  mainPage.getTitleType();
       mainPage.goToNewsPage();

       Assert.assertEquals(titleType,newsPage.getTitleType(),"Title and header should be the same");
    }
}
