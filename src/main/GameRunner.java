package main;

import logic.core.World;
import ui.GameUI;

import java.awt.*;
import java.io.UnsupportedEncodingException;

public class GameRunner {
    public static void main(String[] args){
        Runnable gameRunner = () -> {
            try {
                World.init(new GameUI());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        };

        EventQueue.invokeLater(gameRunner);
    }
}
