package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;

public class Trainer implements Serializable {

    String name;
    int rank;
    boolean offensive;

    public Trainer(int division,boolean topClubs,boolean off){
        offensive = off;
        name = Resources.names.removeFirst();
        if(division == 3 && topClubs) rank = 2;
        else if(division == 3) rank = 1;
        if(division == 2 && topClubs) rank = 3;
        else if(division == 2) rank = 2;
        if(division == 1 && topClubs) rank = 5;
        else if(division == 1) rank = 4;
    }

}
