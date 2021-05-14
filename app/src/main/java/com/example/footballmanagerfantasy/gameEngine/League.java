package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

class League implements Serializable {

    public static final int numberOfGames = 34;

    HashMap<String, Club> clubs = new HashMap<>();
    ArrayList<LinkedList<Game>> calendar = new ArrayList<>();

    @Override
    public String toString() {
        return "League{" +
                "clubs=" + clubs +
                '}';
    }

    public void makeCalender() {

        ArrayList<String> clubNames = new ArrayList<>(clubs.keySet());
        shuffle(clubNames);
        boolean home = true;

        for(int round = 0;round < numberOfGames;round++) {
            calendar.add(round,new LinkedList<>());
            for(int i = 0,j = clubs.size()-1;i < j;i++,j--) {
                String team1 = clubNames.get(i);
                String team2 = clubNames.get(j);
                if(home) calendar.get(round).add(new Game(team1,team2));
                else calendar.get(round).add(new Game(team2,team1));
            }
            home = !home;
            clubNames.add(1, clubNames.remove(clubs.size()-1));
        }

    }

    private void shuffle(ArrayList<String> clubs) {

        int n = clubs.size();
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            int j = r.nextInt(n);
            String temp = clubs.get(i);
            clubs.set(i, clubs.get(j));
            clubs.set(j, temp);
        }
    }
}

