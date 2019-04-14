package stepDefs;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driverSingleton.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.VacancyPage;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class MyStepdefs {
    LoginPage loginPage ;
    MainPage mainPage;
    VacancyPage vacancyPage;
    WebDriver driver;

    @Before
    public void start(Scenario scenario){
        System.out.println("I start execute scenario "+scenario.getName());

        driver = WebDriverSingleton.create();
        loginPage = new LoginPage();
        mainPage = new MainPage();
        vacancyPage = new VacancyPage();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void finish(){
        System.out.println("I kill my driver \n");
        WebDriverSingleton.kill();
    }

    @When("^I (open|am on) main page$")
    public void iOpenMainPage(String navigationType) {
        System.out.println("I open main page");
        mainPage.open();
    }

    @And("^I click on login icon$")
    public void iClickOnLoginIcon() {
        System.out.println("I click on login icon");
        mainPage.goToLoginPage();
    }

    @And("^I click on login button$")
    public void iClickOnLoginButton() {
        System.out.println(" click on login button");
        loginPage.clickToLoginButton();
    }

    @Then("^I should see error message$")
    public void iShouldSeeErrorMessage() {
        System.out.println("I should see error message");
        String negativeLoginMessage = loginPage.getNegativeLoginMessage();

        String message = "Неверный логин или пароль.";
        String messageByMaster = "Error message should be 'Неверный логин или пароль.'";
        Assert.assertEquals(negativeLoginMessage,message,messageByMaster);
    }

    @And("^I go to login page$")
    public void iGoToLoginPage() {
        mainPage.goToLoginPage();
        System.out.println("I go to login page");
    }

    @And("^I enter \"([^\"]*)\" as login$")
    public void iEnterAsLogin( String login ) {
        System.out.println("I enter "+ login+" as login");
        loginPage.inputLogin(login);
    }

    @And("^I enter \"([^\"]*)\" password$")
    public void iEnterPassword(String password) {
        System.out.println("I enter "+ password+" as password");
        loginPage.inputPassword(password);
    }

    @And("^I login with \"([^\"]*)\" as login and \"([^\"]*)\" as password$")
    public void iLoginWithAsLoginAndAsPassword(String login, String password)  {
        System.out.println("I login with "+login+" as login and "+password+" as password");
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickToLoginButton();
    }

    @And("^Я логинюсь со следующими данными$")
    public void iLoginWithTheFollowingCredentials(DataTable table) {
        System.out.println("");
    }

    @Then("^I should see 5 error message$")
    public void iShouldSeeErrorMessage(int arg0) {
        System.out.println("");
    }

    @And("^I enter <Login> as login$")
    public void iEnterLoginAsLogin(DataTable login) {
        System.out.println(login);
    }

    @When("^I click on iSoft vacancies$")
    public void iClickOnISoftVacancies() {
        mainPage.goToISoftVacancies();
    }

    @Then("^I should be redirected to vacancy page$")
    public void iShouldBeRedirectedToVacancyPage() {
        Assert.assertTrue(vacancyPage.isOpened(), "Vacancy page should be opened");
    }

    @And("^I should see that vacancy menu item is active$")
    public void iShouldSeeThatVacancyMenuItemIsActive() {
        Boolean isVacancyActive = vacancyPage.isHeaderItemActive("Вакансии");
        Assert.assertTrue(isVacancyActive, "Vacancy class should be active");
    }

    @And("^I should see that vacancy item colour is different$")
    public void iShouldSeeThatVacancyItemColourIsDifferent() {
        String salaryColour = vacancyPage.getHeaderItemColour("Зарплаты");
        String vacancyColour = vacancyPage.getHeaderItemColour("Вакансии");
        Assert.assertNotEquals(salaryColour, vacancyColour, "Colours should be different");
    }

    @Then("^I should see main page should be opened$")
    public void iShouldSeeMainPageShouldBeOpened() {
        Assert.assertTrue(mainPage.isOpened());
    }

    @And("^I should see auth icon$")
    public void iShouldSeeAuthIcon() {
        Assert.assertTrue(mainPage.isAutentificated());
    }
}
