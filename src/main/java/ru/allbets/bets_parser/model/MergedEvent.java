package ru.allbets.bets_parser.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude
public class MergedEvent {
    private String eventName;
    private Map<String, Map<String, Double>> bookmakerCoeffs;

    public MergedEvent() {
    }

    public MergedEvent(String eventName, Map<String, Map<String, Double>> bookmakerCoeffs) {
        this.eventName = eventName;
        this.bookmakerCoeffs = bookmakerCoeffs;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Map<String, Map<String, Double>> getBookmakerCoeffs() {
        return bookmakerCoeffs;
    }

    public void setBookmakerCoeffs(Map<String, Map<String, Double>> bookmakerCoeffs) {
        this.bookmakerCoeffs = bookmakerCoeffs;
    }
}
