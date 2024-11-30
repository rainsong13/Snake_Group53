package game;

import game.model.GameMain;

public class StartGame {

    public static void start() {
        // Create the main game instance to start the game
        new GameMain();
    }

    public static void main(String[] args) {
        StartGame.start();
    }
}
