package ru.allbets.bets_parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.allbets.bets_parser.service.ContextWrapper;
import ru.allbets.bets_parser.service.Parser;

@SpringBootApplication
public class BetsParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetsParserApplication.class, args);
//        ContextWrapper.getContext().getBean(Parser.class).start();
    }

}
