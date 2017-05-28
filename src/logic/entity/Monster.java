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
import logic.core.dice.DiceRoller;
import logic.enums.MonsterDice;
import logic.item.LootItem;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Entity {
    @Expose private int ID;

    @Expose private String name;
    @Expose private int rewardExperiencePoints;
    @Expose private int rewardGold;
    @Expose private int toHitModifier;
    @Expose private int armorClass;
    @Expose private List<Integer> dicePool;

    @Expose private List<LootItem> lootTable;

    public Monster(int id, String name, int rewardExperiencePoints, int rewardGold, List<Integer> dicePool,
                   List<Integer> stats){
        super(stats);
        ID = id;
        this.name = name;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;
        this.dicePool = dicePool;
        lootTable = new ArrayList<>();

        rollHealth();
    }

    private void rollHealth(){
        setMaxHitPoints(DiceRoller.rollDice(getDicePool(MonsterDice.NUM_HIT_DICE),
                getDicePool(MonsterDice.HIT_DIE_TYPE), getDicePool(MonsterDice.HIT_DIE_MODIFIER)));

        setCurrentHitPoints(getMaxHitPoints());
    }

    public int getID(){
        return ID;
    }

    public String getName() {
        return name;
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

    public List<Integer> getDicePoolList(){
        return dicePool;
    }

    public int getDicePool(MonsterDice dieType) {
        return dicePool.get(dieType.getValue());
    }

    public int getToHitModifier() {
        return toHitModifier;
    }

    public int getArmorClass() {
        return armorClass;
    }
}
