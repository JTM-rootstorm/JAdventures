package rpg.ui.components;

import rpg.logic.World;
import rpg.logic.observer.GameObserver;

import javax.swing.*;

public class GameCboBox<E> extends JComboBox {
    private GameObserver observer;

    public GameCboBox(){
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
