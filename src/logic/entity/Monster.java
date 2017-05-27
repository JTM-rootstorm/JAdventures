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
import logic.item.LootItem;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Entity {
    @Expose private int ID;

    @Expose private String name;
    @Expose private int maxDamage;
    @Expose private int rewardExperiencePoints;
    @Expose private int rewardGold;

    @Expose private List<LootItem> lootTable;

    public Monster(int id, String name, int maxDamage, int rewardExperiencePoints,
            int rewardGold, int currentHitPoints, int maxHitPoints, List<Integer> stats){
        super(currentHitPoints, maxHitPoints, stats);
        ID = id;
        this.name = name;
        this.maxDamage = maxDamage;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;

        lootTable = new ArrayList<>();
    }

    public int getID(){
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getMaxDamage(){
        return maxDamage;
    }

    public int getRewardExperiencePoints(){
        return rewardExperiencePoints;
    }

    public int getRewardGold(){
        return rewardGold;
    }

    public List<LootItem> getLootTable(){
        return lootTable;
    }
}
