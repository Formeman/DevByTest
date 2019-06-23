package pages.staticPages;

import elements.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

/**
 * Created by User on 18.03.2019.
 */
public class StaticVacancyPage extends BasePage {
    public StaticVacancyPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    private static final By header = By.xpath("//div[@class = 'header-global__header-nav-content']");
    private static final Header desktopHeader = new Header(header);

    public String getHeaderItemColour(String menuItemName) {
        return desktopHeader.getColour(menuItemName);
    }

    public Boolean isHeaderItemActive(String menuItemName) {
        return desktopHeader.isItemActive(menuItemName);
    }

    public boolean isOpened() {
        return driver.getCurrentUrl().startsWith("https://jobs.dev.by/");
    }
}
