package rpg.main;

import rpg.logic.World;
import rpg.ui.GameUI;

import java.awt.*;

public class GameRunner {
    public static void main(String[] args){
        Runnable gameRunner = () -> {
            World.populateItems();
            World.populateMonsters();
            World.populateQuests();
            World.populateLocations();
            GameUI gameUI = new GameUI();
        };

        EventQueue.invokeLater(gameRunner);
    }
}
