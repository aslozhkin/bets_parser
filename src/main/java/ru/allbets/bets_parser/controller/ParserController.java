package ru.allbets.bets_parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.allbets.bets_parser.model.MergedEvent;
import ru.allbets.bets_parser.dao.EventRepository;
import ru.allbets.bets_parser.entity.Event;
import ru.allbets.bets_parser.service.ContextWrapper;
import ru.allbets.bets_parser.service.Parser;
import ru.allbets.bets_parser.utils.EventUtils;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ParserController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/run_parse")
    public String runParser() {
        ContextWrapper.getContext().getBean(Parser.class).start();
        return "Parser was run successfully!";
    }

    @GetMapping("/events/random")
    public MergedEvent getRandomEvent() {
        long count = eventRepository.count();
        Event event = eventRepository.findAll().get((int) (Math.random() * count));
        int firstTeamId = event.getFirstTeamId();
        int secondTeamId = event.getSecondTeamId();
        List<Event> events = eventRepository.findAllByFirstTeamIdAndSecondTeamId(firstTeamId, secondTeamId);
        return EventUtils.mergeEvents(events);
    }

    @GetMapping("/events/{count}")
    public List<MergedEvent> getMergedEvents(@PathVariable int count) {
        List<MergedEvent> mergedEvents = new ArrayList<>();

        int eventsToMergeCount = 0;
        List<Event> events = new ArrayList<>();
        while (eventsToMergeCount < count) {
            long eventsCount = eventRepository.count();
            Event event = eventRepository.findAll().get((int) (Math.random() * eventsCount));
            int firstTeamId = event.getFirstTeamId();
            int secondTeamId = event.getSecondTeamId();
            List<Event> eventsWithSameTeamIDs = eventRepository.findAllByFirstTeamIdAndSecondTeamId(firstTeamId, secondTeamId);
            boolean eventContains = false;
            for (Event eventsWithSameTeamID : eventsWithSameTeamIDs) {
                if (events.contains(eventsWithSameTeamID)) {
                    eventContains = true;
                    break;
                }
            }
            if (!eventContains) {
                events.add(event);
                eventsToMergeCount++;
            }
        }

        for (Event event : events) {
            int firstTeamId = event.getFirstTeamId();
            int secondTeamId = event.getSecondTeamId();
            mergedEvents.add(EventUtils.mergeEvents(eventRepository.findAllByFirstTeamIdAndSecondTeamId(firstTeamId, secondTeamId)));
        }
        return mergedEvents;
    }
}
