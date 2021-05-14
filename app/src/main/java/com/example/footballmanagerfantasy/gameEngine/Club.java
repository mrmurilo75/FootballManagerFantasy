package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;
import java.util.HashMap;

class Club implements Serializable {

    int points;
    int wins;
    int draws;
    int losses;
    int minObjective;
    int maxObjective;
    Manager manager;
    Trainer offTrainer;
    Trainer deffTrainer;
    Medic medic;

    HashMap<String, Player> players = new HashMap<>();

    public Club(int minObjective, int maxObjective) {
        points = 0;
        wins = 0;
        draws = 0;
        losses = 0;
        this.minObjective = minObjective;
        this.maxObjective = maxObjective;
        manager = new Manager();
        offTrainer = new Trainer();
        deffTrainer = new Trainer();
        medic = new Medic();
    }

    @Override
    public String toString() {
        return "Club{" +
                "points=" + points +
                ", wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", minObjective=" + minObjective +
                ", maxObjective=" + maxObjective +
                ", manager=" + manager +
                ", offTrainer=" + offTrainer +
                ", deffTrainer=" + deffTrainer +
                ", medic=" + medic +
                ", players=" + players +
                '}';
    }
}
