package rpg.main;

import rpg.logic.World;
import rpg.ui.GameUI;

import java.awt.*;

public class GameRunner {
    public static void main(String[] args){
        Runnable gameRunner = () -> {
            World.init();
            World.setGameUI(new GameUI());
        };

        EventQueue.invokeLater(gameRunner);
    }
}
