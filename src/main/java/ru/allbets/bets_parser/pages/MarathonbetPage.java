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
    private static final String URL = "https://www.marathonbet.ru/su/popular/Football?interval=H24";

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

                firstTeam.addEventToFirstTeam(event);
                secondTeam.addEventToSecondTeam(event);
                league.addEventToLeague(event);

                event.setTeamFirstWinCoeff(Double.parseDouble(eventData.get("teamFirstWinCoeff")));
                event.setDrawCoeff(Double.parseDouble(eventData.get("drawCoeff")));
                event.setTeamSecondWinCoeff(Double.parseDouble(eventData.get("teamSecondWinCoeff")));
                event.setTeamFirstWinOrDrawCoeff(Double.parseDouble(eventData.get("teamFirstWinOrDrawCoeff")));
                event.setTeamFirstWinOrSecondCoeff(Double.parseDouble(eventData.get("teamFirstWinOrSecondCoeff")));
                event.setTeamSecondWinOrDrawCoeff(Double.parseDouble(eventData.get("teamSecondWinOrDrawCoeff")));
                event.setBkId(1);

//                eventRepository.save(event);
                leagueRepository.save(league);
            }
            driver.navigate().back();
        }
    }

    @Override
    public Map<String, String> getEventData(WebElement webElement) {
        Map<String, String> data = new HashMap<>();
        data.put("firstTeamName", webElement.findElement(By.xpath(".//table[@class='member-area-content-table ']//tr[1]//span")).getText());
        data.put("secondTeamName",webElement.findElement(By.xpath(".//table[@class='member-area-content-table ']//tr[2]//span")).getText());
        data.put("teamFirstWinCoeff",webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Match_Result.1')]")).getText());
        data.put("drawCoeff",webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Match_Result.draw')]")).getText());
        data.put("teamSecondWinCoeff",webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Match_Result.3')]")).getText());
        data.put("teamFirstWinOrDrawCoeff",webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Result.HD')]")).getText());
        data.put("teamFirstWinOrSecondCoeff",webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Result.HA')]")).getText());
        data.put("teamSecondWinOrDrawCoeff",webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Result.AD')]")).getText());

        return data;
    }
}
