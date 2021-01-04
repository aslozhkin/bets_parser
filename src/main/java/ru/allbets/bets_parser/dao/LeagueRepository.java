package ru.allbets.bets_parser.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.allbets.bets_parser.entity.League;

public interface LeagueRepository extends JpaRepository<League, Integer> {
}
