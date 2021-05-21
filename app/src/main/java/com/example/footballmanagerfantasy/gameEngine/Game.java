package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;
import java.util.Arrays;

class Game implements Serializable {

    String home;
    String away;
    int[] result;

    public Game(String home, String away){
        this.home = home;
        this.away = away;
    }

    @Override
    public String toString() {
        return "Game{" +
                "home='" + home + '\'' +
                ", away='" + away + '\'' +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}

