package ru.allbets.bets_parser.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allbets.bets_parser.exceptions.NoSuchLeagueException;
import ru.allbets.bets_parser.dao.BookmakerRepository;
import ru.allbets.bets_parser.dao.EventRepository;
import ru.allbets.bets_parser.dao.LeagueRepository;
import ru.allbets.bets_parser.dao.TeamRepository;
import ru.allbets.bets_parser.entity.Bookmaker;
import ru.allbets.bets_parser.entity.Event;
import ru.allbets.bets_parser.entity.League;
import ru.allbets.bets_parser.entity.Team;
import ru.allbets.bets_parser.utils.DriverManager;

import java.util.*;

@Component
public class MarathonbetPage extends AbstractPage {
    private final String url = "https://www.marathonbet.ru/su/betting/Football";
    private final String name = "Марафон Бет";
    private final String engPremiereLeague = "/England/Premier+League";
    private final String spainPremiereLeague = "/Spain/Primera+Division";
    private final String portugalPremiereLeague = "/Portugal/Primeira+Liga";

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
        logger.info("Start parsing Marathonbet");

        Bookmaker bookmaker = bookmakerRepository.findByName(name);

        WebDriver driver = driverManager.getDriver();
//        iter leagues
        List<League> leagues = leagueRepository.findAll();
        for (League league : leagues) {
            switch (league.getMarathonBetName()) {
                case "Англия. Премьер-лига" : driver.get(url + engPremiereLeague);
                break;
                case "Испания. Примера дивизион" : driver.get(url + spainPremiereLeague);
                break;
                case "Португалия. Примейра-лига" : driver.get(url + portugalPremiereLeague);
                break;
                default: throw new NoSuchLeagueException("Отсутствует лига: \"" + league.getMarathonBetName() + "\"");
            }
                List<WebElement> events = driver.findElements(By.xpath("//table[@class='coupon-row-item']"));
                for (WebElement eventElement : events) {
                    Event event = new Event();
                    Map<String, String> eventData = getEventData(eventElement);

                    Team firstTeam = teamRepository.findByMarathonBetName(eventData.get("firstTeamName"));
                    Team secondTeam = teamRepository.findByMarathonBetName(eventData.get("secondTeamName"));

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
        }
        logger.info("End parsing Marathonbet");
        driverManager.shutDownDriver();
    }

    @Override
    public Map<String, String> getEventData(WebElement webElement) {
        Map<String, String> data = new HashMap<>();
        data.put("firstTeamName", webElement.findElement(By.xpath(".//table[@class='member-area-content-table ']//tr[1]//span")).getText());
        data.put("secondTeamName", webElement.findElement(By.xpath(".//table[@class='member-area-content-table ']//tr[2]//span")).getText());
        data.put("teamFirstWinCoeff", webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Match_Result.1')]")).getText());
        data.put("drawCoeff", webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Match_Result.draw')]")).getText());
        data.put("teamSecondWinCoeff", webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Match_Result.3')]")).getText());
        data.put("teamFirstWinOrDrawCoeff", webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Result.HD')]")).getText());
        data.put("teamFirstWinOrSecondCoeff", webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Result.HA')]")).getText());
        data.put("teamSecondWinOrDrawCoeff", webElement.findElement(By.xpath(".//span[contains(@data-selection-key,'Result.AD')]")).getText());

        return data;
    }
}
