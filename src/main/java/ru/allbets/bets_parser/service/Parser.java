package ru.allbets.bets_parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allbets.bets_parser.dao.EventRepository;
import ru.allbets.bets_parser.pages.AbstractPage;
import ru.allbets.bets_parser.pages.FonbetPage;
import ru.allbets.bets_parser.pages.MarathonbetPage;

import java.util.List;

@Component
public class Parser {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private FonbetPage page;

    public void start() {
        eventRepository.deleteAll();
//        eventRepository.setIdToZero(); - #todo Написать запрос на обнуление id таблицы events
        page.parseEvents();
    }

//    @Autowired
//    private List<AbstractPage> pages;
//
//    public void start() {
//        for (AbstractPage page : pages) {
//            page.parseEvents();
//        }
//    }

}
