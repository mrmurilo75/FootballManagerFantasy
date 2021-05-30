package com.example.footballmanagerfantasy.gameEngine;

public class GameEngine {

    private static GameState gameState;

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        GameEngine.gameState = gameState;
    }
}
