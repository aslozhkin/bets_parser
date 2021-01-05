package ru.allbets.bets_parser.exceptions;

public class NoSuchLeagueException extends RuntimeException {
    public NoSuchLeagueException(String message) {
        super(message);
    }
}
