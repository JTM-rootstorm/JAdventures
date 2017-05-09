/*
   JAdventure - A Java-based RPG
   Copyright (C) 2017  TehGuy

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package ui.components;

import logic.core.World;
import logic.observer.GameObserver;

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
