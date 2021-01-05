package ru.allbets.bets_parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.allbets.bets_parser.dao.BookmakerRepository;
import ru.allbets.bets_parser.dao.TeamRepository;
import ru.allbets.bets_parser.entity.Bookmaker;
import ru.allbets.bets_parser.entity.Event;
import ru.allbets.bets_parser.entity.Team;
import ru.allbets.bets_parser.service.ContextWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude
public class MergedEvent {
    private String firstTeamName;
    private String secondTeamName;
    private Map<String, Map<String, Double>> bookmakerCoeffs;

    private MergedEvent(String firstTeamName, String secondTeamNamem, Map<String, Map<String, Double>> bookmakerCoeffs) {
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamNamem;
        this.bookmakerCoeffs = bookmakerCoeffs;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public void setFirstTeamName(String firstTeamName) {
        this.firstTeamName = firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public void setSecondTeamName(String secondTeamName) {
        this.secondTeamName = secondTeamName;
    }

    public Map<String, Map<String, Double>> getBookmakerCoeffs() {
        return bookmakerCoeffs;
    }

    public void setBookmakerCoeffs(Map<String, Map<String, Double>> bookmakerCoeffs) {
        this.bookmakerCoeffs = bookmakerCoeffs;
    }

    public static MergedEvent mergeEvents(List<Event> eventsToMerge) {
        String firstTeamName;
        String secondTeamName;
        Map<String, Map<String, Double>> bookmakerCoeffs = new HashMap<>();

        TeamRepository teamRepository = ContextWrapper.getContext().getBean(TeamRepository.class);
        BookmakerRepository bookmakerRepository = ContextWrapper.getContext().getBean(BookmakerRepository.class);

        Team firstTeam = teamRepository.findById(eventsToMerge.get(0).getFirstTeamId()).get();
        Team secondTeam = teamRepository.findById(eventsToMerge.get(0).getSecondTeamId()).get();
        firstTeamName = firstTeam.getMarathonBetName();
        secondTeamName = secondTeam.getMarathonBetName();
        for (Event event : eventsToMerge) {
            Bookmaker bookmaker = bookmakerRepository.findById(event.getBkId()).get();
            bookmakerCoeffs.put(bookmaker.getName(), event.getAllCoeffs());
        }
        return new MergedEvent(firstTeamName, secondTeamName, bookmakerCoeffs);
    }
}
