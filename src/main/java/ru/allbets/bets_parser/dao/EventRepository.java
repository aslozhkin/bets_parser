package ru.allbets.bets_parser.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.allbets.bets_parser.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
