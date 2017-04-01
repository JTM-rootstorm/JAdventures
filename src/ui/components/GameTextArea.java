package ui.components;

import logic.core.World;
import logic.observer.MessageObserver;

import javax.swing.*;

public class GameTextArea extends JTextArea {
    private MessageObserver observer;

    public GameTextArea(){
        super();
    }

    public void addObserver(MessageObserver observer){
        if(observer == null){
            return;
        }

        this.observer = observer;
        World.addMessengerObserver(this.observer);
    }

    public void removeObserver(){
        World.removeMessengerObserver(observer);
    }
}
