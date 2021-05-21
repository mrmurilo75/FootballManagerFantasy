package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;

class Medic implements Serializable {

    String name;
    int rank; // 3 division have 1-2 starts - 2 division have 2-3 starts - 1 division have 4-5 starts

    public Medic(int division, boolean topClubs ){
        name = Resources.names.removeFirst();
        if(division == 3 && topClubs) rank = 2;
        else if(division == 3) rank = 1;
        if(division == 2 && topClubs) rank = 3;
        else if(division == 2) rank = 2;
        if(division == 1 && topClubs) rank = 5;
        else if(division == 1) rank = 4;
    }


}