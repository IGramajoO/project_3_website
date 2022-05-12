package com.example.project_3_website;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teamId", nullable = false)
    private int teamId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teamId")
    List<Heroes> heroesList= new ArrayList<>();

    public List<Heroes> getHeroesList() {
        return heroesList;
    }

    public void addHeroes(Heroes hero){
        heroesList.add(hero);
    }

    public void setHeroesList(List<Heroes> heroesList) {
        this.heroesList = heroesList;
    }

    public int getId() {
        return teamId;
    }

    public void setId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", heroesList=" + heroesList +
                '}';
    }
}
