package ru.allbets.bets_parser.pages;

import org.openqa.selenium.WebElement;

import java.util.Map;

public abstract class AbstractPage extends Thread{
    @Override
    public void run() {
        parseEvents();
    }

    public abstract void parseEvents();

    public abstract Map<String, String> getEventData(WebElement eventElement);
}
