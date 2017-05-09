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

package logic.entity;

import com.google.gson.annotations.Expose;

public class Entity{
    @Expose private int currentHitPoints;
    @Expose private int maxHitPoints;

    Entity(int currentHitPoints, int maxHitPoints){
        this.currentHitPoints = currentHitPoints;
        this.maxHitPoints = maxHitPoints;
    }

    public int getCurrentHitPoints(){
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int hitPoints){
        currentHitPoints = hitPoints;
    }

    int getMaxHitPoints(){
        return maxHitPoints;
    }

    public void setMaxHitPoints(int hitPoints){
        maxHitPoints = hitPoints;
    }
}
