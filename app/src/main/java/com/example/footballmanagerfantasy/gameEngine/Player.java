package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;

public class Player implements Serializable {

    public String position;
    public int age;
    public int rank;
    public int fieldPos; //positive value is on field, negative is on bench -1,-2,-3 ...
    public int value;


    public Player(String pos,int division,int maxObjective){
        age = RandomGenerator.getPlayerAge();
        rank = RandomGenerator.getPlayerRank(division,maxObjective);
        position = pos;
        value = rank - age;
        if(value <= 0) value = 1;
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

