package ru.allbets.bets_parser.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import ru.allbets.bets_parser.exceptions.NoSuchLeagueException;
import ru.allbets.bets_parser.utils.DriverManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PariMatchPage extends AbstractPage {
    private final String url = "https://www.parimatch.ru/ru/football";
    private final String name = "PARIMATCH";

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
        logger.info("Start parsing PariMatch");

        Bookmaker bookmaker = bookmakerRepository.findByName(name);

        WebDriver driver = driverManager.getDriver();
        driver.get(url);
//        iter leagues
        List<League> leagues = leagueRepository.findAll();
        for (League league : leagues) {
            if (league.getPariMatchName() == null || league.getPariMatchName().isEmpty()) break;
            WebElement leagueLink = driver.findElement(By.xpath("//div[text()='" + league.getPariMatchName() + "']"));
            leagueLink.click();
//            Parse events
            List<WebElement> events = driver.findElements(By.xpath("//div[@data-id='event-card']"));

            WebElement eventToRemove = null;
            try {
//                    Если есть элемент с текстом "Итоги", то его необходимо удалить
                events.get(events.size() - 1).findElement(By.xpath(".//span[contains(text(),'Итоги')]"));
                eventToRemove = events.get(events.size() - 1);
            } catch (NoSuchElementException ignore) {}

            events.remove(eventToRemove);

            for (int i = 0; i < events.size(); i++) {
                Event event = new Event();
                driver.findElements(By.xpath("//div[@data-id='event-card']")).get(i)
                        .findElement(By.xpath(".//a[@data-id='event-card-main-info-button']")).click();

                Map<String, String> eventData = getEventData();

                Team firstTeam = teamRepository.findByPariMatchName(eventData.get("firstTeamName"));
                if (firstTeam == null) {
                    logger.error("Команда с именем: \"" + eventData.get("firstTeamName") + "\" не найдена в таблице команд \"" + name + "\"");
                    driver.navigate().back();
                    continue;
                }
                Team secondTeam = teamRepository.findByPariMatchName(eventData.get("secondTeamName"));
                if (secondTeam == null) {
                    logger.error("Команда с именем: \"" + eventData.get("secondTeamName") + "\" не найдена в таблице команд \"" + name + "\"");
                    driver.navigate().back();
                    continue;
                }

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

                driver.navigate().back();

            }
            leagueLink.click();
        }
        logger.info("End parsing PariMatch");
        driverManager.shutDownDriver();
    }

    @Override
    public Map<String, String> getEventData(WebElement... webElements) {
        WebDriver driver = driverManager.getDriver();

        Map<String, String> data = new HashMap<>();
        String firstTeamName = driver.findElement(
                By.xpath(".//div[contains(@data-id,'event-view-prematch-infoboard')]//div[contains(@data-id,'competitor')][1]")).getText();
        String secondTeamName = driver.findElement(
                By.xpath(".//div[contains(@data-id,'event-view-prematch-infoboard')]//div[contains(@data-id,'competitor')][2]")).getText();
        data.put("firstTeamName", firstTeamName);
        data.put("secondTeamName", secondTeamName);
        data.put("teamFirstWinCoeff",
                driver.findElement(By.xpath(".//div[@data-id='outcome']//div[text()='" + firstTeamName + "']/..//span")).getText());
        data.put("drawCoeff",
                driver.findElement(By.xpath(".//div[@data-id='outcome']//div[text()='Ничья']/..//span")).getText());
        data.put("teamSecondWinCoeff",
                driver.findElement(By.xpath(".//div[@data-id='outcome']//div[text()='" + secondTeamName + "']/..//span")).getText());
        data.put("teamFirstWinOrDrawCoeff",
                driver.findElement(By.xpath(".//div[@data-id='outcome']//div[text()='" + firstTeamName + " не проиграет']/..//span")).getText());
        data.put("teamFirstWinOrSecondCoeff",
                driver.findElement(By.xpath(".//div[@data-id='outcome']//div[text()='Не будет ничьей']/..//span")).getText());
        data.put("teamSecondWinOrDrawCoeff",
                driver.findElement(By.xpath(".//div[@data-id='outcome']//div[text()='" + secondTeamName + " не проиграет']/..//span")).getText());
        return data;
    }
}
