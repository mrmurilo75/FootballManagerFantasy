package com.example.footballmanagerfantasy.gameEngine;

import java.util.Random;

public class RandomGenerator {

    public final static int seed = 873981924;
    public final static Random r = new java.util.Random(seed);

    public static int getPlayerAge(){
        return 17 + r.nextInt(24); //17-40
    }

    public static int getManagerAge(){
        return 40 + r.nextInt(21); //40-60
    }

    public static int getPlayerRank(int division,int maxObjective){
        int bound = 11;

        if(division == 1){
            if(maxObjective == 1) return 90 + r.nextInt(bound); // 1 - 3 : 90-100
            if(maxObjective == 2) return 80 + r.nextInt(bound); // 4 - 6 : 80-90
            if(maxObjective == 5) return 70 + r.nextInt(bound); // 7 - 18 : 70-80
        }
        else if(division == 2){
            if(maxObjective == 1) return 70 + r.nextInt(bound); // 1 - 3 : 70-80
            if(maxObjective == 2) return 60 + r.nextInt(bound); // 4 - 6 : 60-70
            if(maxObjective == 5) return 50 + r.nextInt(bound); // 7 - 18 : 50-60
        }
        else{
            if(maxObjective == 1) return 50 + r.nextInt(bound); // 1 - 3 : 50-60
            if(maxObjective == 2) return 40 + r.nextInt(bound); // 4 - 6 : 40-50
            if(maxObjective == 5) return 30 + r.nextInt(bound); // 7 - 18 : 50-60
        }
        return -1;
    }

}
