package ru.allbets.bets_parser.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.allbets.bets_parser.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findByMarathonBetName(String name);

    Team findByFonbetName(String name);
}
