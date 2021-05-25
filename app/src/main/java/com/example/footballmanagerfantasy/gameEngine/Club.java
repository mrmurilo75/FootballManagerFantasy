package com.example.footballmanagerfantasy.gameEngine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Club implements Serializable {

    /**
     * Minimum of : 2 goolkepers
     *              5 defenders
     *              5 midflienders
     *              5 atackers
     */

    public int points;
    public int wins;
    public int draws;
    public int losses;
    public int goalsConceded;
    public int goalsScored;
    public int minObjective;
    public int maxObjective;
    Manager manager;
    Trainer offTrainer;
    Trainer deffTrainer;
    Medic medic;
    Tactics tactics;

    HashMap<String,Player> players = new HashMap<>();

    public Club(int division,int minObjective, int maxObjective) {
        points = 0;
        wins = 0;
        goalsConceded = 0;
        goalsScored = 0;
        draws = 0;
        losses = 0;
        this.minObjective = minObjective;
        this.maxObjective = maxObjective;
        boolean topClub = maxObjective == 1 || maxObjective == 2;
        manager = new Manager(division,topClub);
        tactics = new Tactics();
        offTrainer = new Trainer(division,topClub,true);
        deffTrainer = new Trainer(division,topClub,false);
        medic = new Medic(division,topClub);
    }

    @Override
    public String toString() {
        return "Club{" +
                "points=" + points +
                ", wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", goalsConceded=" + goalsConceded +
                ", goalsScored=" + goalsScored +
                ", minObjective=" + minObjective +
                ", maxObjective=" + maxObjective +
                ", manager=" + manager +
                ", offTrainer=" + offTrainer +
                ", deffTrainer=" + deffTrainer +
                ", medic=" + medic +
                ", tactics=" + tactics +
                ", players=" + players +
                '}';
    }

    /**
     * @return an array with 26 names of the players that are on the field,
     * each index represents one position on the field, if the there is no player in
     * that position than that name is "empty".
     * See comment in method to understand.
     */
     /* ----------------------
     *  |     ---------      |
     *  |     |       |      |
     *  |                    |
     *  | 21  22  23  24  25 | Strikers
     *  | 16  17  18  19  20 | Offensive Midfielder
     *  | 11  12  13  14  15 | Midfielder
     *  | 6   7   8   9   10 | Defensive Midfielders
     *  | 1   2   3   4   5  | Defense
     *  |     ---------      |
     *  |     |   0   |      | Goalkeeper
     *  ----------------------
     *
     */
    public String[] getOnfield(){

        String[] p = new String[26];

       for(int i = 0; i < p.length;i++){
           p[i] = getPos(i);
       }
       return p;
    }

    private String getPos(int pos){

        for( String playerName : players.keySet() ){
            Player p = players.get(playerName);
            if(p.fieldPos == pos) return playerName;
        }
        return "empty";
    }

    /**
     * @return the names of the players that are substitutes ordered according to
     * position: Goalkeeper,Defenders,Midfielders,Attackers.
     */
    public String[] getSubstitutes(){

        int n = (players.size() - 11);
        String[] p = new String[n];
        int j = -1;
        for(int i = 0; i < n;i++){
            p[i] = getPos(j--);
        }
        return p;

    }

    public double[] getChances(){

        double gk = 0;
        double avgDef = 0;
        int defC = 0;
        double avgDefMid = 0;
        int defMidC = 0;
        double avgMid = 0;
        int midC = 0;
        double avgOffMid = 0;
        int offMidC = 0;
        double avgAtt = 0;
        int attC = 0;

        for(String playerName : players.keySet()){
            Player p = players.get(playerName);
            if(p.fieldPos < 0) continue;
            if(p.fieldPos == 0) gk = p.rank;
            else if(p.fieldPos <= 5)  { avgDef += p.rank; defC++; }
            else if(p.fieldPos <= 10) { avgDefMid += p.rank; defMidC++; }
            else if(p.fieldPos <= 15) { avgMid += p.rank; midC++; }
            else if(p.fieldPos <= 20) { avgOffMid += p.rank; offMidC++; }
            else { avgAtt += p.rank; attC++; }
        }
        gk /= 100;
        avgDef = (avgDef / defC) / 100;
        if(defMidC != 0) avgDefMid = (avgDefMid/defMidC) / 100;
        avgMid = (avgMid/midC) / 100;
        if(avgOffMid != 0) avgOffMid = (avgOffMid / offMidC) / 100;
        avgAtt = (avgAtt / attC) / 100;

        double chanceToScore = (attC * 0.1 + offMidC * 0.07 + midC * 0.05) + ( 0.2 * (avgAtt * 0.5 + avgOffMid * 0.3 + avgMid * 0.2) );
        double chanceToDefend = (defC * 0.1 + defMidC * 0.07 + midC * 0.05) + ( 0.2 * (avgDef * 0.5 + avgDefMid * 0.3 + avgMid * 0.2) ) + (0.1 * gk);

        int numberChances = tactics.playStyle == Tactics.PlayStyle.Offensive ? 6 :
                tactics.playStyle == Tactics.PlayStyle.Balance ? 4 : 2;

        if(tactics.attackStyle == Tactics.AttackStyle.Possession && tactics.passStyle == Tactics.PassStyle.Short){
            chanceToScore += 0.1;
        }
        else if(tactics.attackStyle == Tactics.AttackStyle.Counter && tactics.passStyle == Tactics.PassStyle.Direct){
            chanceToScore += 0.1;
        }
        else if(tactics.attackStyle == Tactics.AttackStyle.UseWings && tactics.passStyle == Tactics.PassStyle.Long){
            chanceToScore += 0.1;
        }
        if(tactics.shootingStyle == Tactics.ShootingStyle.OnSight){
            chanceToScore -= 0.1;
            numberChances++;
        }
        else if(tactics.shootingStyle == Tactics.ShootingStyle.InsideTheArea){
            chanceToScore += 0.1;
            numberChances--;
        }
        if(tactics.defenseStyle == Tactics.DefenseStyle.HighLine && tactics.marking == Tactics.Marking.Zone){
            chanceToDefend += 0.1;
        }
        if(tactics.defenseStyle == Tactics.DefenseStyle.LowLine && tactics.marking == Tactics.Marking.ManToMan){
            chanceToDefend += 0.1;
        }

        return new double[]{chanceToDefend,chanceToScore,numberChances};
    }

    public void assignPositions(){

        class R{
            String name;
            int rank;

            public R(String n,int r){
                name = n;
                rank = r;
            }
        }

        PriorityQueue<R> gk = new PriorityQueue<>(5,(p1,p2) -> p1.rank - p2.rank);
        PriorityQueue<R> def = new PriorityQueue<>(5,(p1,p2) -> p1.rank - p2.rank);
        PriorityQueue<R> mid = new PriorityQueue<>(5,(p1,p2) -> p1.rank - p2.rank);
        PriorityQueue<R> att = new PriorityQueue<>(5,(p1,p2) -> p1.rank - p2.rank);

        for( String playerName : players.keySet()){
            Player p = players.get(playerName);
            switch (p.position){
                case "G":
                    gk.add(new R(playerName,p.rank));
                    break;
                case "D":
                    def.add(new R(playerName,p.rank));
                    break;
                case "M":
                    mid.add(new R(playerName,p.rank));
                    break;
                case "A":
                    att.add(new R(playerName,p.rank));
                    break;
            }
        }
        players.get(gk.poll().name).fieldPos = 0;

        int[] formation = null;

        for( int[] f : tactics.getFormation() ){
            formation = f;
            int defCount = 0;
            int midCount = 0;
            int attCount = 0;

            for (int p : f) {
                if (p <= 5) defCount++;
                else if (p <= 15) midCount++;
                else attCount++;
            }
            if (defCount <= def.size() && midCount <= mid.size() && attCount <= att.size()) break;
        }

        tactics.formation = formation;

        for(int p : formation){
            if (p <= 5) players.get(def.poll().name).fieldPos = p;
            else if (p <= 15) players.get(mid.poll().name).fieldPos = p;
            else players.get(att.poll().name).fieldPos = p;
        }
        for(int i = -1; !att.isEmpty() ;i--){
            R p = !gk.isEmpty() ? gk.poll() : !mid.isEmpty() ? mid.poll() : att.poll();
            players.get(p.name).fieldPos = i;
        }
    }
}
