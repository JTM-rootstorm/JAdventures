package ui.components;

import logic.core.World;
import logic.observer.GameObserver;

import javax.swing.*;

public class GameLabel extends JLabel {
    private GameObserver observer;

    public GameLabel(){
        super();
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
