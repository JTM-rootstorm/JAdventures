package rpg.ui.components;

import rpg.logic.World;
import rpg.logic.observer.GameObserver;

import javax.swing.*;

public class GameButton extends JButton {
    private GameObserver observer;

    public GameButton(){
        super();
    }

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
