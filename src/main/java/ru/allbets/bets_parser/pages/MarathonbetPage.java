package ru.allbets.bets_parser.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allbets.bets_parser.utils.DriverManager;

import java.util.List;

@Component
public class MarathonbetPage extends AbstractPage {
    private static final String URL = "https://www.marathonbet.ru/su/popular/Football";

    @Autowired
    private DriverManager driverManager;

    @Override
    public void parseEvents() {
        WebDriver driver = driverManager.getDriver();
        driver.get(URL);
        List<WebElement> categoryContainers = driver.findElements(By.xpath("//*[@class='category-container']"));
        for (WebElement categoryContainer : categoryContainers) {
            categoryContainer.findElement(By.xpath(".//*[@class='category-label-link']")).click();
//            Parse events
            driver.navigate().back();
        }
    }
}
