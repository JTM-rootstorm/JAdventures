package ui.components;

import logic.core.World;
import logic.observer.GameObserver;

import javax.swing.*;
import javax.swing.table.TableModel;

public class GameTable extends JTable {
    private GameObserver observer;

    public GameTable(){
        super();
    }

    public GameTable(TableModel dm){
        super(dm);
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
