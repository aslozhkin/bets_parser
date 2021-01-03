package ru.allbets.bets_parser.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leagues")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "league", fetch = FetchType.EAGER)
    List<Event> events;

    public League() {
    }

    public League(String name) {
        this.name = name;
    }

    public void addEventToLeague(Event event) {
        if (events == null) events = new ArrayList<>();
        events.add(event);
        event.setLeague(this);
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
}
