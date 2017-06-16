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

package logic.core.dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiceRoller {

    private DiceRoller(){

    }

    public static int rollDice(int numberOfDice, int sidesOnDie, int modifier){
        return RandomNumberGenerator.NumberBetween(numberOfDice, (numberOfDice * sidesOnDie)) + modifier;
    }

    public static int rollAbilityScore(int numberOfDice){
        List<Integer> rollList = new ArrayList<>();

        for (int i = 0; i < numberOfDice; i++){
            rollList.add(RandomNumberGenerator.NumberBetween(1, 6));
        }

        if (numberOfDice == 2){
            rollList.add(6);
        }

        rollList.sort(Collections.reverseOrder());

        return rollList.get(0) + rollList.get(1) + rollList.get(2);
    }
}
