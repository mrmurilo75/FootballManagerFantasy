package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;

class Player implements Serializable {

    String position;
    int age;
    int rank;

    public Player(String pos){
        age = Resources.ages.removeFirst();
        rank = Resources.rankings.removeFirst();
        position = pos;
    }

    @Override
    public String toString() {
        return "Player{" +
                "position='" + position + '\'' +
                ", age=" + age +
                ", rank=" + rank +
                '}';
    }
}

