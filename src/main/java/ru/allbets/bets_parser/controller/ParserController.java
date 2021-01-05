package ru.allbets.bets_parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.allbets.bets_parser.MergedEvent;
import ru.allbets.bets_parser.dao.EventRepository;
import ru.allbets.bets_parser.entity.Event;
import ru.allbets.bets_parser.service.ContextWrapper;
import ru.allbets.bets_parser.service.Parser;

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

    @GetMapping("/events/first")
    public MergedEvent getRandomEvent() {
        Event event = eventRepository.findAll().get(0);
        int firstTeamId = event.getFirstTeamId();
        int secondTeamId = event.getSecondTeamId();
        List<Event> events = eventRepository.findAllByFirstTeamIdAndSecondTeamId(firstTeamId, secondTeamId);
        return MergedEvent.mergeEvents(events);
    }
}
