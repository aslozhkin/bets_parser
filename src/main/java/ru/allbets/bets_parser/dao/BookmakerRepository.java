package ru.allbets.bets_parser.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.allbets.bets_parser.entity.Bookmaker;

public interface BookmakerRepository extends JpaRepository<Bookmaker, Integer> {
    Bookmaker findByName(String name);
}
