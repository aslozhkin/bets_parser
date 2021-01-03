package ru.allbets.bets_parser.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "league_id")
    private int leagueId;

    @Column(name = "id_team_first")
    private int firstTeamId;

    @Column(name = "id_team_second")
    private int secondTeamId;

    @Column(name = "id_bk")
    private int bkId;

    @Column(name = "win_first")
    private double teamFirstWinCoeff;

    @Column(name = "draw")
    private double drawCoeff;

    @Column(name = "win_second")
    private double teamSecondWinCoeff;

    @Column(name = "win_first_or_draw")
    private double teamFirstWinOrDrawCoeff;

    @Column(name = "win_first_or_second")
    private double teamFirstWinOrSecondCoeff;

    @Column(name = "win_second_or_draw")
    private double teamSecondWinOrDrawCoeff;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_id")
    League league;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", fetch = FetchType.EAGER)
    List<Team> teams;

    public Event() {
    }

    public void addTeamToEvent(Team team) {
        if (teams == null) teams = new ArrayList<>();
        teams.add(team);
        team.setEvent(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(int firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public int getSecondTeamId() {
        return secondTeamId;
    }

    public void setSecondTeamId(int secondTeamId) {
        this.secondTeamId = secondTeamId;
    }

    public int getBkId() {
        return bkId;
    }

    public void setBkId(int bkId) {
        this.bkId = bkId;
    }

    public double getTeamFirstWinCoeff() {
        return teamFirstWinCoeff;
    }

    public void setTeamFirstWinCoeff(double teamFirstWinCoeff) {
        this.teamFirstWinCoeff = teamFirstWinCoeff;
    }

    public double getDrawCoeff() {
        return drawCoeff;
    }

    public void setDrawCoeff(double drawCoeff) {
        this.drawCoeff = drawCoeff;
    }

    public double getTeamSecondWinCoeff() {
        return teamSecondWinCoeff;
    }

    public void setTeamSecondWinCoeff(double teamSecondWinCoeff) {
        this.teamSecondWinCoeff = teamSecondWinCoeff;
    }

    public double getTeamFirstWinOrDrawCoeff() {
        return teamFirstWinOrDrawCoeff;
    }

    public void setTeamFirstWinOrDrawCoeff(double teamFirstWinOrDrawCoeff) {
        this.teamFirstWinOrDrawCoeff = teamFirstWinOrDrawCoeff;
    }

    public double getTeamFirstWinOrSecondCoeff() {
        return teamFirstWinOrSecondCoeff;
    }

    public void setTeamFirstWinOrSecondCoeff(double teamFirstWinOrSecondCoeff) {
        this.teamFirstWinOrSecondCoeff = teamFirstWinOrSecondCoeff;
    }

    public double getTeamSecondWinOrDrawCoeff() {
        return teamSecondWinOrDrawCoeff;
    }

    public void setTeamSecondWinOrDrawCoeff(double teamSecondWinOrDrawCoeff) {
        this.teamSecondWinOrDrawCoeff = teamSecondWinOrDrawCoeff;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
