package main;

import logic.core.World;
import ui.GameUI;

import java.awt.*;

public class GameRunner {
    public static void main(String[] args){
        Runnable gameRunner = () -> World.init(new GameUI());

        EventQueue.invokeLater(gameRunner);
    }
}
