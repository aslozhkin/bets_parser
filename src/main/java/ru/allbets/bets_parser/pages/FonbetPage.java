package ru.allbets.bets_parser.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allbets.bets_parser.dao.BookmakerRepository;
import ru.allbets.bets_parser.dao.EventRepository;
import ru.allbets.bets_parser.dao.LeagueRepository;
import ru.allbets.bets_parser.dao.TeamRepository;
import ru.allbets.bets_parser.entity.Bookmaker;
import ru.allbets.bets_parser.entity.Event;
import ru.allbets.bets_parser.entity.League;
import ru.allbets.bets_parser.entity.Team;
import ru.allbets.bets_parser.utils.DriverManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FonbetPage extends AbstractPage {
    private final String url = "https://www.fonbet.ru/";
    private final String name = "Фонбет";

    @Autowired
    private DriverManager driverManager;

    @Autowired
    private BookmakerRepository bookmakerRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;

    private final Logger logger = LogManager.getLogger(MarathonbetPage.class);

    @Override
    public void parseEvents() {
        logger.debug("Start parsing Fonbet");

        Bookmaker bookmaker = bookmakerRepository.findByName(name);

        WebDriver driver = driverManager.getDriver();
        driver.get(url);
        driver.findElement(By.xpath("//div[@title='Футбол']/span[1]")).click();
//        iter leagues
        List<League> leagues = leagueRepository.findAll();
        for (League league : leagues) {
            WebElement leagueLink = driver.findElement(By.xpath("//span[text()='" + league.getFonbetName() + "']"));
            leagueLink.click();
//            Parse events
            List<WebElement> events = driver.findElements(By.xpath("//div[contains(@class,'top-event-list')]/div"));
            for (WebElement eventElement : events) {
                Event event = new Event();
                Map<String, String> eventData = getEventData(eventElement);

                Team firstTeam = teamRepository.findByFonbetName(eventData.get("firstTeamName"));
                Team secondTeam = teamRepository.findByFonbetName(eventData.get("secondTeamName"));

                event.setLeagueId(league.getId());
                event.setFirstTeamId(firstTeam.getId());
                event.setSecondTeamId(secondTeam.getId());
                event.setBkId(bookmaker.getId());

                event.setTeamFirstWinCoeff(Double.parseDouble(eventData.get("teamFirstWinCoeff")));
                event.setDrawCoeff(Double.parseDouble(eventData.get("drawCoeff")));
                event.setTeamSecondWinCoeff(Double.parseDouble(eventData.get("teamSecondWinCoeff")));
                event.setTeamFirstWinOrDrawCoeff(Double.parseDouble(eventData.get("teamFirstWinOrDrawCoeff")));
                event.setTeamFirstWinOrSecondCoeff(Double.parseDouble(eventData.get("teamFirstWinOrSecondCoeff")));
                event.setTeamSecondWinOrDrawCoeff(Double.parseDouble(eventData.get("teamSecondWinOrDrawCoeff")));

                eventRepository.save(event);

            }
            driver.findElement(By.xpath("//div[contains(@title,'" + league.getFonbetName() + "')]//span[2]")).click();
        }
        logger.debug("End parsing Fonbet");
    }

    @Override
    public Map<String, String> getEventData(WebElement webElement) {
        WebDriver driver = driverManager.getDriver();
        Map<String, String> data = new HashMap<>();
        data.put("firstTeamName", webElement.findElement(By.xpath(".//a[contains(@class,'title-teams')]//span[1]")).getText());
        data.put("secondTeamName",webElement.findElement(By.xpath(".//a[contains(@class,'title-teams')]//span[2]")).getText());
        data.put("teamFirstWinCoeff",webElement.findElement(By.xpath(".//div[@class='top-event-item__market-cell--1owrF'][1]//span")).getText());
        data.put("drawCoeff",webElement.findElement(By.xpath(".//div[@class='top-event-item__market-cell--1owrF'][2]//span")).getText());
        data.put("teamSecondWinCoeff",webElement.findElement(By.xpath(".//div[@class='top-event-item__market-cell--1owrF'][3]//span")).getText());
        driver.findElement(By.xpath("//a[contains(text(),'Двойные шансы')]")).click();
        List<WebElement> doubleChances = driver.findElements(By.xpath("//div[@class='top-event-item__market-cell--1owrF _style_row--HSj5r']/span[2]"));
        data.put("teamFirstWinOrDrawCoeff", doubleChances.get(0).getText());
        data.put("teamFirstWinOrSecondCoeff", doubleChances.get(1).getText());
        data.put("teamSecondWinOrDrawCoeff", doubleChances.get(2).getText());
        driver.findElement(By.xpath("//a[contains(text(),'Исходы')]")).click();

        return data;
    }
}
