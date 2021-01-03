package ru.allbets.bets_parser.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allbets.bets_parser.dao.EventRepository;
import ru.allbets.bets_parser.dao.LeagueRepository;
import ru.allbets.bets_parser.dao.TeamRepository;
import ru.allbets.bets_parser.entity.Event;
import ru.allbets.bets_parser.entity.League;
import ru.allbets.bets_parser.entity.Team;
import ru.allbets.bets_parser.utils.DriverManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MarathonbetPage extends AbstractPage {
    private static final String URL = "https://www.marathonbet.ru/su/popular/Football";

    @Autowired
    private DriverManager driverManager;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void parseEvents() {
        WebDriver driver = driverManager.getDriver();
        driver.get(URL);
        List<WebElement> categoryContainers = driver.findElements(By.xpath("//*[@class='category-container']"));
//        iter leagues
        for (WebElement categoryContainer : categoryContainers) {
            League league = leagueRepository.findById(1).get();
            categoryContainer.findElement(By.xpath(".//*[@class='category-label-link']")).click();
//            Parse events
            List<WebElement> events = driver.findElements(By.xpath("//table[@class='coupon-row-item']"));
            for (WebElement eventElement : events) {
                Event event = new Event();
                Map<String, String> eventData = getEventData(eventElement);

                Team firstTeam = teamRepository.findByName(eventData.get("firstTeamName"));
                Team secondTeam = teamRepository.findByName(eventData.get("secondTeamName"));

                event.addTeamToEvent(firstTeam);
                event.addTeamToEvent(secondTeam);
                league.addEventToLeague(event);

                eventRepository.save(event);
            }
            driver.navigate().back();
        }
    }

    @Override
    public Map<String, String> getEventData(WebElement webElement) {
        Map<String, String> data = new HashMap<>();
        String firstTeamName = webElement.findElement(By.xpath(".//table[@class='member-area-content-table ']//tr[1]//span")).getText();
        String secondTeamName = webElement.findElement(By.xpath(".//table[@class='member-area-content-table ']//tr[2]//span")).getText();

        return data;
    }
}
