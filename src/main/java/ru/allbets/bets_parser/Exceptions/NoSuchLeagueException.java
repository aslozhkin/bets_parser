package ru.allbets.bets_parser.Exceptions;

public class NoSuchLeagueException extends RuntimeException {
    public NoSuchLeagueException(String message) {
        super(message);
    }
}
