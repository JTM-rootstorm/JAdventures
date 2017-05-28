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

import logic.core.dice.DiceRoller;
import logic.entity.Entity;
import logic.enums.MonsterDice;
import logic.enums.StatArray;
import logic.item.HealingPotion;
import logic.item.InventoryItem;
import logic.item.LootItem;
import logic.item.Weapon;

import java.util.ArrayList;
import java.util.List;

public class CombatLogic {
    private CombatLogic(){

    }

    public static void useWeapon(Object cboObject) {
        Weapon weapon;

        if (cboObject instanceof Weapon) {
            weapon = (Weapon) cboObject;
        } else {
            return;
        }

        int damageToMonster = DiceRoller.rollDice(weapon.getNumAttackDice(), weapon.getSidesOnDie(),
                getAttackModifier(weapon.getAttackStat(), World.getPlayer()));

        World.getCurrentMonster().setCurrentHitPoints(World.getCurrentMonster().getCurrentHitPoints() - damageToMonster);

        World.sendMessengerObserverNotification("message",
                "You hit the " + World.getCurrentMonster().getName() + " for " + damageToMonster
                        + " points.\n");

        if (World.getCurrentMonster().getCurrentHitPoints() <= 0) {
            World.sendMessengerObserverNotification("message", "\nYou defeated the "
                    + World.getCurrentMonster().getName() + "\n");

            World.getPlayer().addExperiencePoints(World.getCurrentMonster().getRewardExperiencePoints());
            World.sendMessengerObserverNotification("message", "You receive "
                    + World.getCurrentMonster().getRewardExperiencePoints() + " experience points.\n");

            World.getPlayer().addGold(World.getCurrentMonster().getRewardGold());
            World.sendMessengerObserverNotification("message", "You receive "
                    + World.getCurrentMonster().getRewardGold() + " gold.\n");

            List<InventoryItem> lootedItems = new ArrayList<>();

            for (LootItem lootItem : World.getCurrentMonster().getLootTable()) {
                if (DiceRoller.rollDice(1, 100, 0) <= lootItem.getDropPercentage()) {
                    lootedItems.add(new InventoryItem(lootItem.getDetails().getID(), 1));
                }
            }

            if (lootedItems.size() == 0) {
                for (LootItem lootItem : World.getCurrentMonster().getLootTable()) {
                    if (lootItem.isDefaultItem()) {
                        lootedItems.add(new InventoryItem(lootItem.getDetails().getID(), 1));
                    }
                }
            }

            for (InventoryItem item : lootedItems) {
                World.getPlayer().addItemToInventory(item.getDetails());

                if (item.getQuantity() == 1) {
                    World.sendMessengerObserverNotification("message", "You loot " + item.getQuantity()
                            + " " + item.getDetails().getName() + "\n");
                } else {
                    World.sendMessengerObserverNotification("message", "You loot " + item.getQuantity()
                            + " " + item.getDetails().getNamePlural() + "\n");
                }
            }

            World.sendMessengerObserverNotification("message", "\n");

            LocationLogic.refreshLocation();
        } else {
            monsterAttack();
        }
    }

    private static void monsterAttack() {
        int damageToPlayer = DiceRoller.rollDice(World.getCurrentMonster().getDicePool(MonsterDice.NUM_ATTACK_DICE),
                World.getCurrentMonster().getDicePool(MonsterDice.ATTACK_DIE_TYPE), World.getCurrentMonster().getDicePool(MonsterDice.ATTACK_DIE_MODIFIER));

        World.sendMessengerObserverNotification("message", "The "
                + World.getCurrentMonster().getName() + " did " + damageToPlayer + " points of damage.\n");

        World.getPlayer().setCurrentHitPoints(World.getPlayer().getCurrentHitPoints() - damageToPlayer);

        if (World.getPlayer().getCurrentHitPoints() <= 0) {
            World.sendMessengerObserverNotification("message", "The "
                    + World.getCurrentMonster().getName() + " killed you.\n");

            LocationLogic.moveToPlayerHome();
        }
    }

    public static void drinkPotion(Object cboObject) {
        HealingPotion potion;

        if (cboObject instanceof HealingPotion) {
            potion = (HealingPotion) cboObject;
        } else {
            return;
        }

        World.getPlayer().addHitPoints(DiceRoller.rollDice(potion.getNumberOfHealDice(), potion.getSidesOnDie(),
                potion.getModifier()));

        if (World.getPlayer().getCurrentHitPoints() > World.getPlayer().getMaxHitPoints()) {
            World.getPlayer().setCurrentHitPoints(World.getPlayer().getMaxHitPoints());
        }

        for (InventoryItem item : World.getPlayer().getInventory()) {
            if (item.getDetails().getID() == potion.getID()) {
                item.setQuantity(item.getQuantity() - 1);
                break;
            }
        }

        World.sendMessengerObserverNotification("message", "You drink a " + potion.getName() + "\n");

        monsterAttack();
    }

    private static int getAttackModifier(StatArray abs, Entity attacker){

        return (int)Math.ceil((attacker.getAbilityScore(abs) - 10) / 2);
    }
}
