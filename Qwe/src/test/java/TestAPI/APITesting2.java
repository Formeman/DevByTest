package TestAPI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class APITesting2 {

    public static void main(String[] args) throws IOException {
        WebDriver driver= new ChromeDriver();
        driver.get("http://dev.by/");

        List<WebElement> linkElements = driver.findElements(By.xpath("//*[@href]"));

        Set<String> links= new HashSet<String>();

        for (WebElement element: linkElements) {
            links.add(element.getAttribute("href"));

        }
        driver.quit();
        for (String href : links) {
            URL obj = new URL(href);
            HttpURLConnection con;

            if (href.contains("https://")) {
                con = (HttpsURLConnection) obj.openConnection();
            } else {
                con = (HttpURLConnection) obj.openConnection();
            }
            con.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

            con.setRequestMethod("GET");

            if (con.getResponseCode() > 200) {
                System.out.println(href + " " + con.getResponseCode());
            }

        }
    }
}



