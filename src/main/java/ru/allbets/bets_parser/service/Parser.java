package ru.allbets.bets_parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allbets.bets_parser.dao.EventRepository;
import ru.allbets.bets_parser.pages.AbstractPage;

import java.util.List;

@Component
public class Parser {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private List<AbstractPage> pages;

    public void start() {
        eventRepository.deleteAll();
//        #todo Написать запрос на обнуление id таблицы events
        for (AbstractPage page : pages) {
            page.start();
//            page.parseEvents();
        }
    }

}
