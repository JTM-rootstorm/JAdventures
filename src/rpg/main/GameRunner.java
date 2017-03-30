package rpg.main;

import rpg.logic.World;
import rpg.ui.GameUI;

import java.awt.*;

public class GameRunner {
    public static void main(String[] args){
        Runnable gameRunner = () -> {
            World.init();
            GameUI gameUI = new GameUI();
        };

        EventQueue.invokeLater(gameRunner);
    }
}
