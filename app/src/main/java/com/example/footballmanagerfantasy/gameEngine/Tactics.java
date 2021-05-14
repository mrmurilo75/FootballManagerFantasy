package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;
import java.util.Random;

public class Tactics implements Serializable {

    private final static Random random = new Random();
    PlayStyle playStyle;
    PassStyle passStyle;
    AttackStyle attackStyle;
    DefenseStyle defenseStyle;
    ShootingStyle shootingStyle;
    Marking marking;
    Formation formation;


    public Tactics(PlayStyle p,PassStyle pass,AttackStyle att,DefenseStyle def,ShootingStyle st,Marking mark){
        playStyle = p;
        passStyle = pass;
        attackStyle = att;
        defenseStyle = def;
        shootingStyle = st;
        marking = mark;
    }
    public Tactics(){
        playStyle = PlayStyle.values()[random.nextInt(PlayStyle.values().length)];
        passStyle = PassStyle.values()[random.nextInt(PassStyle.values().length)];
        attackStyle = AttackStyle.values()[random.nextInt(AttackStyle.values().length)];
        defenseStyle = DefenseStyle.values()[random.nextInt(DefenseStyle.values().length)];
        shootingStyle = ShootingStyle.values()[random.nextInt(ShootingStyle.values().length)];
        marking = Marking.values()[random.nextInt(Marking.values().length)];
        formation = Formation.values()[random.nextInt(Formation.values().length)];
    }

    public enum PlayStyle{
        Offensive,
        Defensive,
        Balance
    }
    public enum PassStyle{
        Short,
        Long,
        Direct
    }
    public enum AttackStyle{
        Possession,
        Counter,
        UseWings
    }
    public enum DefenseStyle{
        HighLine,
        LowLine,
    }
    public enum ShootingStyle{
        OnSight,
        InsideTheArea
    }
    public enum Marking {
        ManToMan,
        Zone
    }
    //https://en.wikipedia.org/wiki/Formation_(association_football)#Common_modern_formations
    public enum Formation{
        _4_3_3,
        _4_4_2,
        _4_4_2_DIAMOND,
        _4_4_1_1,
        _4_3_2_1,
        _5_3_2,
        _3_4_3,
        _3_5_2,
        _3_6_1,
        _4_5_1,
        _4_2_3_1,
        _5_4_1,
        _4_2_2_2
    }
}
