package ru.allbets.bets_parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allbets.bets_parser.pages.MarathonbetPage;

@Component
public class Parser {
    @Autowired
    private MarathonbetPage page;

    public void start() {
        page.parseEvents();
    }

}
