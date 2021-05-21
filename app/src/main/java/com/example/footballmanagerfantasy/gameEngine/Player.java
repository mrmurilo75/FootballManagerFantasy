package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;

class Player implements Serializable {

    String position;
    int age;
    int rank;
    int fieldPos; //positive value is on field, negative is on bench -1,-2,-3 ...

    public Player(String pos,int division,int maxObjective){
        age = RandomGenerator.getPlayerAge();
        rank = RandomGenerator.getPlayerRank(division,maxObjective);
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

