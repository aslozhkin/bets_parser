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

    @Column(name = "marathonbet_name")
    private String marathonBetName;

    @Column(name = "fonbet_name")
    private String fonbetName;

    public League() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarathonBetName() {
        return marathonBetName;
    }

    public void setMarathonBetName(String marathonBetName) {
        this.marathonBetName = marathonBetName;
    }

    public String getFonbetName() {
        return fonbetName;
    }

    public void setFonbetName(String fonbetName) {
        this.fonbetName = fonbetName;
    }
}
