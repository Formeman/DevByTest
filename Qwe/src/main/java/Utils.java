import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utils {
    public static boolean  isMobileView(WebDriver driver) {
        String className = driver.findElement(By.tagName("html")).getAttribute("class");
        return !className.contains("no-touchevents ");
    }
}
