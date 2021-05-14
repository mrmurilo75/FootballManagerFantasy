package com.example.footballmanagerfantasy.gameEngine;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;


public class GameState implements Serializable{

    private static final String gameStateFile = "gameState.ser";
    Context context;

    HashMap<Integer, League> clubsAndPlayers;
    int currentRound;

    public GameState(Context ct) {
        currentRound = 0;
        context = ct;
        clubsAndPlayers = new HashMap<>();
        initialize();
        System.out.println(clubsAndPlayers);
        for(int key : clubsAndPlayers.keySet()){
            clubsAndPlayers.get(key).makeCalender();
        }

        saveGameState();
    }

    /**
     * Simulates all games and updates the classification
     */
    public void simulateGames(){

        for(int key : clubsAndPlayers.keySet()){
            League l = clubsAndPlayers.get(key);

            for( Game g : l.calendar.get(currentRound) ){

                Club homeTeam = l.clubs.get(g.away);
                Club awayTeam = l.clubs.get(g.home);

                //TODO not finished
            }

        }

    }

    /**
    Saves the current game state, should be executed to save the game
     */
    public void saveGameState(){

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try{
            fos = context.openFileOutput(gameStateFile, Context.MODE_PRIVATE);
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
            int minObjective = 1;
            int maxObjective = 1;
            for(int j = 1;j <= 18;j++){
                if(j == 4){
                    minObjective = 2;
                    maxObjective = 4;
                }
                if(j == 7){
                    minObjective = 5;
                    maxObjective = 16;
                }
                Club c = new Club(minObjective,maxObjective);
                l.clubs.put(Resources.clubNames.removeFirst(),c);
                for(int k = 1;k <= 20;k++){
                    String position;
                    if(k <= 3) position = "G";
                    if(k >= 4 && k <= 9) position = "D";
                    if(k >= 10 && k <= 15) position = "M";
                    else position = "A";
                    c.players.put(Resources.names.removeFirst(),new Player(position));
                }
            }
        }
    }
}
