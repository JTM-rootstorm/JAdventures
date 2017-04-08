package ui.components;

import logic.core.World;
import logic.observer.GameObserver;

import javax.swing.*;

public class GameButton extends JButton {
    private GameObserver observer;

    public GameButton(String text){
        super(text);
    }

    public void addObserver(GameObserver observer){
        if(observer == null){
            return;
        }
        this.observer = observer;
        World.addObserverToList(this.observer);
    }

    public void removeObserver(){
        World.removeObserverFromList(observer);
        observer = null;
    }
}
