package com.example.footballmanagerfantasy;

import junit.framework.TestCase;
import java.util.LinkedList;

//public class GameStateTest extends TestCase {
//
//    public String[] clubs = new String[]{
//        "All Stars",
//                "Blitz",
//                "Magic",
//                "Phoenix",
//                "Calvary",
//                "Gators",
//                "Rebels",
//                "Heatwave",
//                "Impact",
//                "Fairies",
//                "Fuego",
//                "Rampage",
//                "Hustle",
//                "Rule Breakers",
//                "Bosses",
//                "Guardians",
//                "Bannermen",
//                "Peak Performers"
//    };
//    public GameState gs = new GameState(clubs);
//    public int n = GameState.numberOfGames;
//
//    public void testNumberGames() {
//        assertEquals(n,gs.calendar.size());
//    }
//
//    public void testNumberTeams() {
//        for(int i = 0;i < gs.calendar.size() ;i++) {
//            LinkedList<Game> j1 = gs.calendar.get(i);
//            assertEquals(9,j1.size());
//        }
//    }
//
//    public void testPlayAll() {
//        for(String club1 : clubs) {
//
//            for(String club2 : clubs) {
//                if(club1.equals(club2)) continue;
//
//                int countHome = 0;
//                int countAway = 0;
//
//                for(int i = 0;i < gs.calendar.size() ;i++) {
//                    LinkedList<Game> j1 = gs.calendar.get(i);
//                    for( Game g : j1) {
//                        if(g.home.equals(club1) && g.away.equals(club2)) countHome++;
//                        if(g.away.equals(club1) && g.home.equals(club2)) countAway++;
//                    }
//                }
//                assertEquals(1,countHome);
//                assertEquals(1,countAway);
//            }
//        }
//    }
//}