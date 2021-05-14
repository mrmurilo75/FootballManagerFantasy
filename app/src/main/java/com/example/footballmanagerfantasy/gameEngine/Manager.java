package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;

class Manager implements Serializable {

    Tactics tactics;
    String name;

    public Manager(){
        tactics = new Tactics();
        name = Resources.names.removeFirst();
    }

}
