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

package logic.core;

import com.google.gson.annotations.Expose;
import logic.entity.Monster;
import logic.item.Item;
import logic.quests.Quest;

public class Location {
    @Expose private int ID;
    @Expose private String name;
    @Expose private String description;

    @Expose private Integer itemRequiredToEnter;
    @Expose private Integer questAvailableHere;
    @Expose private Integer monsterLivingHere;

    @Expose private Integer locationToNorth = null;
    @Expose private Integer locationToEast = null;
    @Expose private Integer locationToSouth = null;
    @Expose private Integer locationToWest = null;

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Item getItemRequiredToEnter() {
        return World.ItemByID(itemRequiredToEnter);
    }

    Quest getQuestAvailableHere() {
        return World.QuestByID(questAvailableHere);
    }

    public Monster getMonsterLivingHere() {
        return World.MonsterByID(monsterLivingHere);
    }

    public Location getLocationToNorth() {
        return World.LocationByID(locationToNorth);
    }

    public Location getLocationToEast() {
        return World.LocationByID(locationToEast);
    }

    public Location getLocationToSouth() {
        return World.LocationByID(locationToSouth);
    }

    public Location getLocationToWest() {
        return World.LocationByID(locationToWest);
    }

    @Override
    public String toString() {
        return getName();
    }
}
