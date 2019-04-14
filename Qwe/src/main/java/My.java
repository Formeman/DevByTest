        import org.openqa.selenium.By;
        import org.openqa.selenium.Dimension;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import java.util.List;
        public class My {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:/D/java/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.google.com/");

        driver.findElement(By.className("gLFyf"))
                .sendKeys("iba курсы автоматизация");

        List<WebElement> searchButtons
                = driver.findElements(By.xpath("//input[@name='btnK']"));

        for (WebElement searchButton:searchButtons) {
            if (searchButton.isDisplayed()) {
                searchButton.click();
                break;
            }
        }

        driver.findElement(By
                .xpath("//a[contains(@href, 'instituteiba')]")).click();

        List<WebElement> languageSwitchers
                = driver.findElements(By.xpath("//a[@href='/en/']"));

        for (WebElement switcher: languageSwitchers) {
            if (switcher.isDisplayed()){
                switcher.click();
                break;
            }
        }

        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = "Institute IBA";

        if (actualPageTitle.contentEquals(expectedPageTitle)) {
            System.out.println("Тест прошёл");
        } else {
            System.out.println("Тест упал");
        }

        driver.manage().window().setSize(new Dimension(80,520));

        if (driver.findElement(By.id("hamburger")).isDisplayed()) {
            System.out.println("Hamburger is displayed");
        } else {
            System.out.println("Hamburger is not displayed");
        }

        Thread.sleep(5000);
        driver.quit();

    }
}
