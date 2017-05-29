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

package ui;

import logic.observer.GameObserver;
import ui.components.GameButton;
import ui.components.GameLabel;

import javax.swing.*;
import java.awt.event.ActionListener;

class ElementCreator {
    private ElementCreator(){

    }

    static JLabel createNormalLabel(String text, int loc_x, int loc_y, int size_x, int size_y){
        JLabel tempLabel = new JLabel(text);
        tempLabel.setLocation(loc_x, loc_y);
        tempLabel.setSize(size_x, size_y);

        return tempLabel;
    }

    static GameLabel createGameLabel(int loc_y, GameObserver gameObserver){
        GameLabel tempLabel = new GameLabel();
        tempLabel.setLocation(110, loc_y);
        tempLabel.setSize(50, 10);
        tempLabel.addObserver(gameObserver);

        return tempLabel;
    }

    static GameButton createGameButton(String text, int loc_x, int loc_y, ActionListener actionListener){
        final int BUTTON_WIDTH = 100;
        final int BUTTON_HEIGHT = 20;

        GameButton tempButton = new GameButton(text);
        tempButton.setLocation(loc_x, loc_y);
        tempButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        tempButton.addActionListener(actionListener);

        return tempButton;
    }
}
