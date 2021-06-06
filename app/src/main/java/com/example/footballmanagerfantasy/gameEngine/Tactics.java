
package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Tactics implements Serializable {

    @Override
    public String toString() {
        return "Tactics{" +
                "playStyle=" + playStyle +
                ", passStyle=" + passStyle +
                ", attackStyle=" + attackStyle +
                ", defenseStyle=" + defenseStyle +
                ", shootingStyle=" + shootingStyle +
                ", marking=" + marking +
                ", formation=" + Arrays.toString(formation) +
                '}';
    }

    /**
     * Matches are simulated according to :
     *
     * chance to score : ( numberMidfielders * 0.05 + numberAttackers * 0.1 + numberOffensiveMidfielders * 0.07 ) +
     *                   ( 0.2 * (attAvg * 0.5 + OffMidAvg * 0.3 + 0.2 * midAvg) )
     * chance to defend : ( 0.1 * numberDefenders + 0.07 * numberDefensiveMidfielders * 0.05 * numberMidfielders ) +
     *                    ( 0.2 * (defAvg * 0.5 + defMidAvg * 0.3 + 0.2 * midAvg) ) + 0.1 * gkRank
     *
     *
     *  playStile:
     *     Offensive = 6 opportunities to score
     *     Defensive = 2 opportunities to score
     *     Balance   = 4 opportunities to score
     *
     * if AttackStyle.Possession && PassStyle.Short : chance to score += 0.1
     * if AttackStyle.Counter && PassStyle.Direct : chance to score += 0.1
     * if AttackStyle.UseWings && PassStyle.Long : chance to score += 0.1
     *
     * if ShootingStyle.OnSight : chance to score += 0.1
     *                            opportunities to score--
     *
     * if DefenseStyle.HighLine && Marking.Zone : chance to defend += 0.1
     *
     * if DefenseStyle.LowLine && Marking.ManToMan : chance to defend += 0.1
     *
     */

    private static final Random random = RandomGenerator.r;

    public PlayStyle playStyle;
    public PassStyle passStyle;
    public AttackStyle attackStyle;
    public DefenseStyle defenseStyle;
    public ShootingStyle shootingStyle;
    public Marking marking;
    public int[] formation;

    public Tactics(PlayStyle p, PassStyle pass, AttackStyle att, DefenseStyle def, ShootingStyle st, Marking mark) {
        playStyle = p;
        passStyle = pass;
        attackStyle = att;
        defenseStyle = def;
        shootingStyle = st;
        marking = mark;
    }

    public Tactics() {
        playStyle = PlayStyle.values()[random.nextInt(PlayStyle.values().length)];
        passStyle = PassStyle.values()[random.nextInt(PassStyle.values().length)];
        attackStyle = AttackStyle.values()[random.nextInt(AttackStyle.values().length)];
        defenseStyle = DefenseStyle.values()[random.nextInt(DefenseStyle.values().length)];
        shootingStyle = ShootingStyle.values()[random.nextInt(ShootingStyle.values().length)];
        marking = Marking.values()[random.nextInt(Marking.values().length)];
    }

    public enum PlayStyle {
        Offensive,
        Defensive,
        Balance
    }

    public enum PassStyle {
        Short,
        Long,
        Direct
    }

    public enum AttackStyle {
        Possession,
        Counter,
        UseWings
    }

    public enum DefenseStyle {
        HighLine,
        LowLine,
    }

    public enum ShootingStyle {
        OnSight,
        InsideTheArea
    }

    public enum Marking {
        ManToMan,
        Zone
    }

    //https://en.wikipedia.org/wiki/Formation_(association_football)#Common_modern_formations

    /**
     * Possible positions in the field
     *
     *
     * | 21  22  23  24  25 | Strikers
     * | 16  17  18  19  20 | Offensive Midfielder
     * | 11  12  13  14  15 | Midfielder
     * | 6   7   8   9   10 | Defensive Midfielders
     * | 1   2   3   4   5  | Defense
     * | -   -   0   -   -  | Goalkeeper
     *
     */
    public int[][] getFormation() {
        int[][] arr = new int[][]{
                {1, 2, 4, 5, 12, 13, 14, 21, 23, 25},  //4-3-3
                {1, 2, 4, 5, 11, 12, 14, 15,22, 24},   //4-4-2
                {1, 2, 4, 5, 8, 12, 14, 18, 22, 24},   //4-4-2 Diamond
                {1, 2, 4, 5, 11, 12, 14, 15, 17, 24},  //4-4-1-1
                {1, 2, 4, 5, 12, 13, 14, 17, 19, 23},  //4-3-2-1
                {1, 2, 3, 4, 5, 12, 13, 14, 22, 24},   //5-3-2
                {2, 3, 4, 11, 12, 14, 15, 21, 23, 25}, //3-4-3
                {1, 3, 4, 7, 8, 11, 15, 18, 22, 24},   //3-5-2
                {2, 3, 4, 7, 9, 11, 15, 17, 19, 23},   //3-6-1
                {1, 2, 4, 5, 11, 12, 13, 14, 15, 23},  //4-5-1
                {1, 2, 4, 5, 7, 9, 11, 15, 18, 23},    //4-2-3-1
                {1, 2, 3, 4, 5, 11, 12, 14, 15, 23},   //5-4-1
                {1, 2, 4, 5, 7, 9, 17, 19, 22, 24}     //4-4-2
        };
        shuffle(arr);
        return arr;
    }
    private void shuffle(int[][] arr) {

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int j = random.nextInt(n);
            int[] temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}