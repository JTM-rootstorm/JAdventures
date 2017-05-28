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

package logic.item;

import com.google.gson.annotations.Expose;
import logic.enums.StatArray;
import logic.enums.WeaponType;

public class Weapon extends Item {
    @SuppressWarnings("unused") @Expose private WeaponType weaponType;
    @SuppressWarnings("unused") @Expose private StatArray attackStat;
    @SuppressWarnings("unused") @Expose private int numAttackDice;
    @SuppressWarnings("unused") @Expose private int sidesOnDie;

    public StatArray getAttackStat(){
        return attackStat;
    }

    public int getNumAttackDice(){
        return numAttackDice;
    }

    public int getSidesOnDie(){
        return sidesOnDie;
    }
}
