package ru.allbets.bets_parser.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Event() {
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

    public Map<String, Double> getAllCoeffs() {
        Map<String, Double> allCoeffs = new HashMap<>();
        allCoeffs.put("Победа 1", teamFirstWinCoeff);
        allCoeffs.put("Ничья", drawCoeff);
        allCoeffs.put("Победа 2", teamSecondWinCoeff);
        allCoeffs.put("Победа 1 или ничья", teamFirstWinOrDrawCoeff);
        allCoeffs.put("Победа 1 или победа 2", teamFirstWinOrSecondCoeff);
        allCoeffs.put("Победа 2 или ничья", teamSecondWinOrDrawCoeff);
        return allCoeffs;
    }
}
