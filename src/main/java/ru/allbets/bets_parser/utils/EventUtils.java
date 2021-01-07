package ru.allbets.bets_parser.utils;

import ru.allbets.bets_parser.dao.BookmakerRepository;
import ru.allbets.bets_parser.dao.TeamRepository;
import ru.allbets.bets_parser.entity.Bookmaker;
import ru.allbets.bets_parser.entity.Event;
import ru.allbets.bets_parser.entity.Team;
import ru.allbets.bets_parser.model.MergedEvent;
import ru.allbets.bets_parser.service.ContextWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventUtils {
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
        return new MergedEvent(firstTeamName + " - " + secondTeamName, bookmakerCoeffs);
    }
}
