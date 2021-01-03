package ru.allbets.bets_parser.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "firstTeam", fetch = FetchType.EAGER)
    private List<Event> firstTeamEvents;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secondTeam", fetch = FetchType.EAGER)
    private List<Event> secondTeamEvents;

    public Team() {
    }

    public void addEventToFirstTeam(Event event) {
        if (firstTeamEvents == null) firstTeamEvents = new ArrayList<>();
        firstTeamEvents.add(event);
        event.setFirstTeam(this);
    }

    public void addEventToSecondTeam(Event event) {
        if (secondTeamEvents == null) secondTeamEvents = new ArrayList<>();
        secondTeamEvents.add(event);
        event.setSecondTeam(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getFirstTeamEvents() {
        return firstTeamEvents;
    }

    public void setFirstTeamEvents(List<Event> firstTeamEvents) {
        this.firstTeamEvents = firstTeamEvents;
    }

    public List<Event> getSecondTeamEvents() {
        return secondTeamEvents;
    }

    public void setSecondTeamEvents(List<Event> secondTeamEvents) {
        this.secondTeamEvents = secondTeamEvents;
    }

    //    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }
}
