package ru.allbets.bets_parser.pages;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public abstract class AbstractPage {

    public abstract void parseEvents();

    public abstract Map<String, String> getEventData(WebElement eventElement);
}
