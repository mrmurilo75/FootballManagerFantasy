package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;
import java.util.Arrays;

public class Game implements Serializable {

    public String home;
    public String away;
    public int[] result;

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

