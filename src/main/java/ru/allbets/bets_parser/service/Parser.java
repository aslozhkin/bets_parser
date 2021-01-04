package ru.allbets.bets_parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allbets.bets_parser.pages.AbstractPage;

import java.util.List;

@Component
public class Parser {
    @Autowired
    private List<AbstractPage> pages;

    public void start() {
        for (AbstractPage page : pages) {
            page.parseEvents();
        }
    }

}
