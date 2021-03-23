package com.example.footballmanagerfantasy;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class League implements Serializable {
    private static final long serialVersionUID = 1L;

    HashMap<String,Club> clubs = new HashMap<>();

    @Override
    public String toString() {
        return "League{" +
                "clubs=" + clubs +
                '}';
    }
}

class Club implements Serializable{
    private static final long serialVersionUID = 1L;

    int points;
    int wins;
    int draws;
    int losses;
    int minObjective;
    int maxObjective;
    HashMap<String,Player> players = new HashMap<>();

    public Club(String[] data){
        this.points = Integer.parseInt(data[0]);
        this.wins = Integer.parseInt(data[1]);
        this.draws = Integer.parseInt(data[2]);
        this.losses = Integer.parseInt(data[3]);
        String[] s = data[4].split("-");
        this.minObjective = Integer.parseInt(s[0]);
        if(s.length < 2) this.maxObjective = 0;
        else this.maxObjective = Integer.parseInt(s[1]);
    }

    @Override
    public String toString() {
        return "Club{" +
                "points=" + points +
                ", wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", minObjective=" + minObjective +
                ", maxObjective=" + maxObjective +
                ", players=" + players +
                '}';
    }
}

class Player implements Serializable{
    private static final long serialVersionUID = 1L;

    String position;
    int age;
    int rank;

    public Player(String[] data){
        this.age = Integer.parseInt(data[0]);
        this.rank = Integer.parseInt(data[1]);
        this.position = data[2];
    }

    @Override
    public String toString() {
        return "Player{" +
                "position='" + position + '\'' +
                ", age=" + age +
                ", rank=" + rank +
                '}';
    }
}

public class DataBase {

    private static final String dataBaseFile = "dataBase.ser";
    static HashMap<Integer,League> dataBase = new HashMap<>();

    public static void loadResources(Context context, boolean newGame){

        if(newGame){
            createDataBase(context,"dataBase.csv",0,null);
            saveDataBase(context);
        }
        else {
            String[] files = context.fileList();
            boolean dataBaseExists = false;
            for (String s : files) {
                if (s.equals(dataBaseFile)) {
                    dataBaseExists = true;
                    break;
                }
            }
            if (dataBaseExists) loadDataBase(context);
            else {
                createDataBase(context, "dataBase.csv", 0, null);
                saveDataBase(context);
            }
        }
    }
    public static void saveDataBase(Context context){

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try{
            fos = context.openFileOutput(dataBaseFile, Context.MODE_PRIVATE);
            out = new ObjectOutputStream(fos);
            out.writeObject(dataBase);
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

    public static void loadDataBase(Context context){

        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = context.openFileInput(dataBaseFile);
            in = new ObjectInputStream(fis);
            dataBase = (HashMap<Integer, League>) in.readObject();
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
    }

    private static void createDataBase(Context context, String filePath, int league, String club){

        boolean leagueFile = false;
        boolean clubFile = false;
        boolean playerFile = false;

        try {
            InputStream in = context.getAssets().open("dataBase/"+filePath);
            Scanner myReader = new Scanner(in);
            boolean first = true;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = data.replaceAll("\"","");
                if(first){
                    String s = data.split(",")[0];
                    if(s.equals("league")){
                        leagueFile = true;
                    }
                    else if(s.equals("club")){
                        clubFile = true;
                    }
                    else if(s.equals("name")){
                        playerFile = true;
                    }
                    first = false;
                    continue;
                }
                String[] splitData = data.split(",");
                String file = splitData[splitData.length-1];

                if(leagueFile){
                    League l = new League();
                    league = Character.getNumericValue(splitData[0].charAt(0));
                    dataBase.put(league,new League());
                }
                else if(clubFile){
                    Club c = new Club(Arrays.copyOfRange(splitData,1,splitData.length-1) );
                    club = splitData[0];
                    dataBase.get(league).clubs.put(splitData[0],c);
                }
                else if(playerFile){
                    Player p = new Player(Arrays.copyOfRange(splitData,1,splitData.length));
                    dataBase.get(league).clubs.get(club).players.put(splitData[0],p);
                    continue;
                }
                createDataBase(context,file,league,club);
            }
            myReader.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
