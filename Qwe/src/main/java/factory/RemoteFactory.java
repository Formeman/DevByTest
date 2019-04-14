package factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteFactory extends WebDriverFactory
{
    @Override
    public WebDriver create(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.acceptInsecureCerts();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("platform", Platform.WINDOWS);

        try {
            return new RemoteWebDriver(new URL("http://172.17.66.233:4445/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
