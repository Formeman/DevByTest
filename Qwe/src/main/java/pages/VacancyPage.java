package pages;

import elements.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 18.03.2019.
 */
public class VacancyPage extends BasePage {
    public VacancyPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    private By header = By.xpath("//div[@class = 'header-global__header-nav-content']");//div[@class = 'header__navigation']
    private Header desktopHeader = new Header(header);

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
