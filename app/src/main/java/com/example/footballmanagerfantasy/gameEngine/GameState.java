package com.example.footballmanagerfantasy.gameEngine;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class GameState implements Serializable{

    private static final String gameStateFile = "gameState.ser";

    //    Context context;
    HashMap<Integer, League> clubsAndPlayers;
    int currentRound;
    String playerClub;
    int playerDivision;

    public GameState() {
        currentRound = 0;
//        context = ct;
        clubsAndPlayers = new HashMap<>();
        initialize();
        for(int leagueName : clubsAndPlayers.keySet()){
            League l = clubsAndPlayers.get(leagueName);
            l.makeCalender();
            for(String clubName : l.clubs.keySet()){
                l.clubs.get(clubName).assignPositions();
            }
        }
//        saveGameState();
    }

    public void initializePlayer(String club,String name){
        playerDivision = 2;
        playerClub = club;
        clubsAndPlayers.get(playerDivision).clubs.get(club).manager = new Manager(name,35);
    }

    public LinkedList<String> getClubsOfDivision(int division) {
        LinkedList<String> clubNames = new LinkedList<>();

        for( String clubName : clubsAndPlayers.get(division).clubs.keySet() ){
            if( clubsAndPlayers.get(division).clubs.get(clubName).minObjective == 16 ){
                clubNames.add(clubName);
            }
        }

        return clubNames;
    }

    public Club getClub(int division,String name){
        return clubsAndPlayers.get(division-1).clubs.get(name);
    }

    public LinkedList<NameAndObj> getClassification(){

        LinkedList<NameAndObj> classification = new LinkedList<>();
        PriorityQueue<NameAndObj> list = new PriorityQueue<>(clubsAndPlayers.get(playerDivision).clubs.size(), (c1,c2) -> {
            Club cl1 = (Club)c1.obj;
            Club cl2 = (Club)c2.obj;
            if(cl1.points == cl2.points){
                int goalDiff1 = cl1.goalsScored - cl1.goalsConceded;
                int goalDiff2 = cl2.goalsScored - cl2.goalsConceded;
                return goalDiff2 - goalDiff1;
            }
            return cl2.points - cl1.points;
        } );

        for( String clubName : clubsAndPlayers.get(playerDivision).clubs.keySet()){
            Club c = clubsAndPlayers.get(playerDivision).clubs.get(clubName);
            list.add(new NameAndObj(clubName,c));
        }

        while (!list.isEmpty()) {
            NameAndObj no = list.poll();
//            Club c = (Club)no.obj;
//            System.out.println(c.points);
            classification.addLast(no);
        }
        return classification;
    }

    public LinkedList<Game> getRoundResults(){
        return clubsAndPlayers.get(playerDivision).calendar.get(currentRound - 1);
    }

    public String[] getPlayerNextGame(){
        LinkedList<Game> games = clubsAndPlayers.get(playerDivision).calendar.get(currentRound);
        for( Game g : games ){
            if(g.home.equals(playerClub) || g.away.equals(playerClub)){
                return new String[]{g.home,g.away};
            }
        }
        return null;
    }

    /**
     * Simulates all games and updates the classification
     */
    public void simulateGames(){

        for(int key : clubsAndPlayers.keySet()){
            League l = clubsAndPlayers.get(key);

            for( Game g : l.calendar.get(currentRound) ){

                Club homeTeam = l.clubs.get(g.home);
                Club awayTeam = l.clubs.get(g.away);

                double homeTeamChances[] = homeTeam.getChances();
                double awayTeamChances[] = awayTeam.getChances();

                int homeGoals = 0;
                int awayGoals = 0;

                int nHome = (int)homeTeamChances[2];
                int nAway = (int)awayTeamChances[2];
                if(playerDivision == key) {
                    System.out.println(g.home);
                    System.out.println(g.away);
                    System.out.println("home : " + homeTeam);
                    System.out.println("away : " + awayTeam);
                    System.out.println("home : " + Arrays.toString(homeTeamChances));
                    System.out.println("away : " + Arrays.toString(awayTeamChances));
                }
                for(int i = 0; i < nHome;i++){
                    if( Math.random() < homeTeamChances[1] && Math.random() > awayTeamChances[0] ){
                        homeGoals++;
                    }
                }
                for(int i = 0;i < nAway;i++){
                    if( Math.random() < awayTeamChances[1] && Math.random() > homeTeamChances[0] ){
                        awayGoals++;
                    }
                }
                g.result = new int[]{homeGoals,awayGoals};
                homeTeam.goalsScored += homeGoals;
                homeTeam.goalsConceded += awayGoals;
                awayTeam.goalsScored += awayGoals;
                awayTeam.goalsConceded += homeGoals;

                if(homeGoals > awayGoals) {
                    homeTeam.wins++;
                    homeTeam.points += 3;
                    awayTeam.losses++;
                }
                else if(awayGoals > homeGoals){
                    awayTeam.wins++;
                    awayTeam.points += 3;
                    homeTeam.losses++;
                }
                else {
                    homeTeam.draws++;
                    homeTeam.points++;
                    awayTeam.draws++;
                    awayTeam.points++;
                }
            }
        }
//        System.out.println(clubsAndPlayers.get(1).calendar.get(currentRound));
        currentRound++;
    }

    /**
     Saves the current game state, should be executed to save the game
     */
    public void saveGameState(Context ct){

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try{
            fos = ct.openFileOutput(gameStateFile, Context.MODE_PRIVATE);
            out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
                if (out != null){
                    out.flush();
                    out.close();
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    /**
     Loads the current saved game if exists or returns null if does't
     */
    public static GameState loadGameState(Context context){

        FileInputStream fis = null;
        ObjectInputStream in = null;
        GameState gs = null;

        try {
            fis = context.openFileInput(gameStateFile);
            in = new ObjectInputStream(fis);
            gs = (GameState) in.readObject();
            in.close();
            fis.close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR" + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
                if (in != null) in.close();
            } catch (Exception e) { e.printStackTrace(); }
        }
        return gs;
    }

    private void initialize(){

        for(int i = 1; i <= 3;i++){
            League l = new League();
            clubsAndPlayers.put(i,l);
            for(int j = 1;j <= 18;j++){
                int minObjective = j <= 3 ? 1 : j <= 6 ? 4 : 16;
                int maxObjective = j <= 3 ? 1 : j <= 6 ? 2 : 5;
                Club c = new Club(i,minObjective,maxObjective);
                l.clubs.put(Resources.clubNames.removeFirst(),c);
                for(int k = 1;k <= 20;k++){
                    String position = k <= 3 ? "G" : k <= 9 ? "D" : k <= 15 ? "M" : "A";
                    c.players.put(Resources.names.removeFirst(),new Player(position,i,maxObjective));
                }
            }
        }
    }

    /**
     * @return a list with all the players in the game,
     * useful for transferMarket to show all the players.
     */
    public LinkedList<Player> getAllPlayers(){

        LinkedList<Player> players = new LinkedList<>();

        for( int leagueName : clubsAndPlayers.keySet() ){
            League l = clubsAndPlayers.get(leagueName);
            for( String clubName : l.clubs.keySet() ){
                Club c = l.clubs.get(clubName);
                for( String playerName : c.players.keySet() ){
                    players.add(c.players.get(playerName));
                }
            }
        }
//        Collections.sort( players, (p1, p2) -> p1.rank -p2.rank );
        return players;
    }
}