package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverFactory extends WebDriverFactory {
    @Override
    public WebDriver create() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("isMobile"))) {
            Map<String, String> emulation = new HashMap<String, String>();
            emulation.put("deviceName", "Nexus 5");
            options.setExperimentalOption("mobileEmulation", emulation);
        }
        if (Boolean.parseBoolean(System.getProperty("isHeadless"))){
            options.addArguments("--headless");
        }
        return new ChromeDriver(options);
    }
}
