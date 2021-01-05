package ru.allbets.bets_parser.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.allbets.bets_parser.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByFirstTeamIdAndSecondTeamId(int firstTeamId, int secondTeamId);

//    @Query("AUTO_INCREMENT = 0")
//    void setIdToZero();
}
