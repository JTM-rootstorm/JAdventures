package rpg.ui.components;

import rpg.logic.World;
import rpg.logic.observer.GameObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameCboBox<E> extends JComboBox {
    private List<GameObserver> observerList = new ArrayList<>();

    public GameCboBox(){
        super();
    }

    public void addObserver(GameObserver observer){
        if(observer == null){
            return;
        }
        observerList.add(observer);
        World.addObserverToList(observer);
    }

    public void removeObservers(){
        for(GameObserver observer : observerList){
            World.removeObserverFromList(observer);
        }
    }
}
