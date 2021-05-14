package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;

class Game implements Serializable {

    String home;
    String away;

    public Game(String home, String away){
        this.home = home;
        this.away = away;
    }

    @Override
    public String toString() {
        return "Game{" +
                "home='" + home + '\'' +
                ", away='" + away + '\'' +
                '}';
    }

}

