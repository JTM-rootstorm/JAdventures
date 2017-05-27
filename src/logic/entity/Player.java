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
import logic.core.DiceRoller;
import logic.core.Location;
import logic.core.LocationLogic;
import logic.core.World;
import logic.item.*;
import logic.quests.PlayerQuest;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    @Expose private int gold;
    @Expose private int expPoints;
    @Expose private int level;

    @Expose private List<InventoryItem> inventory;
    @Expose private List<PlayerQuest> quests;

    @Expose private Integer currentLocation;

    private Weapon currentWeapon = null;

    public Player(int currentHitPoints, int maxHitPoints, List<Integer> stats){
        super(currentHitPoints, maxHitPoints, stats);
        initGold();
        initExpPoints();
        initLevel();
        inventory = new ArrayList<>();
        quests = new ArrayList<>();
    }

    @Override
    public void setCurrentHitPoints(int hitPoints) {
        super.setCurrentHitPoints(hitPoints);
        World.sendObserverNotification("plr_curhp");
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amountToAdd){
        gold += amountToAdd;
    }

    private void initGold() {
        this.gold = 0;
        World.sendObserverNotification("plr_gold");
    }

    public int getExpPoints() {
        return expPoints;
    }

    public void addExperiencePoints(int xp) {
        expPoints += xp;
    }

    private void initExpPoints() {
        this.expPoints = 0;
        World.sendObserverNotification("plr_exp");
    }

    public int getLevel() {
        return level;
    }

    private void initLevel() {
        this.level = 1;
        World.sendObserverNotification("plr_lvl");
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public List<PlayerQuest> getQuests() {
        return quests;
    }

    public Location getCurrentLocation() {
        return World.LocationByID(currentLocation);
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation.getID();
        World.sendMessengerObserverNotification("location",
                "\n\n" + currentLocation.getName() + "\n" + currentLocation.getDescription());
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Object currentWeapon) {
        if (currentWeapon instanceof Weapon) {
            this.currentWeapon = (Weapon) currentWeapon;
        }
    }

    public void addItemToInventory(Item itemToAdd) {
        for (InventoryItem item : inventory) {
            if (item.getDetails().getID() == itemToAdd.getID()) {
                item.incrementQuantity();
                return;
            }
        }

        inventory.add(new InventoryItem(itemToAdd.getID(), 1));

        World.sendObserverNotification("plr_inv_additem");
    }

    public List<Object> getWeapons() {
        List<Object> weapons = new ArrayList<>();

        for (InventoryItem item : inventory) {
            if (item.getDetails() instanceof Weapon && item.getQuantity() > 0) {
                weapons.add(item.getDetails());
            }
        }

        return weapons;
    }

    public List<Object> getPotions() {
        List<Object> potions = new ArrayList<>();

        for (InventoryItem item : inventory) {
            if (item.getDetails() instanceof HealingPotion && item.getQuantity() > 0) {
                potions.add(item.getDetails());
            }
        }

        return potions;
    }

    public Boolean hasRequiredItemToEnter(Location location) {
        if (location.getItemRequiredToEnter() == null) {
            return true;
        }

        for (InventoryItem item : getInventory()) {
            if (item.getDetails().getID() == location.getItemRequiredToEnter().getID()) {
                return true;
            }
        }

        return false;
    }

    public void useWeapon(Object cboObject) {
        Weapon weapon;

        if (cboObject instanceof Weapon) {
            weapon = (Weapon) cboObject;
        } else {
            return;
        }

        int damageToMonster = DiceRoller.rollDice(1, weapon.getMaxDamage(), 0);

        World.getCurrentMonster().setCurrentHitPoints(World.getCurrentMonster().getCurrentHitPoints() - damageToMonster);

        World.sendMessengerObserverNotification("message",
                "You hit the " + World.getCurrentMonster().getName() + " for " + damageToMonster
                        + " points.\n");

        if (World.getCurrentMonster().getCurrentHitPoints() <= 0) {
            World.sendMessengerObserverNotification("message", "\nYou defeated the "
                    + World.getCurrentMonster().getName() + "\n");

            addExperiencePoints(World.getCurrentMonster().getRewardExperiencePoints());
            World.sendMessengerObserverNotification("message", "You receive "
                    + World.getCurrentMonster().getRewardExperiencePoints() + " experience points.\n");

            addGold(World.getCurrentMonster().getRewardGold());
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
                addItemToInventory(item.getDetails());

                if (item.getQuantity() == 1) {
                    World.sendMessengerObserverNotification("message", "You loot " + item.getQuantity()
                            + " " + item.getDetails().getName() + "\n");
                } else {
                    World.sendMessengerObserverNotification("message", "You loot " + item.getQuantity()
                            + " " + item.getDetails().getNamePlural() + "\n");
                }
            }

            World.sendMessengerObserverNotification("message", "\n");

            LocationLogic.moveToLocation(getCurrentLocation());
        } else {
            monsterAttack();
        }
    }

    public void drinkPotion(Object cboObject) {
        HealingPotion potion;

        if (cboObject instanceof HealingPotion) {
            potion = (HealingPotion) cboObject;
        } else {
            return;
        }

        setCurrentHitPoints(getCurrentHitPoints() + potion.getAmountToHeal());

        if (getCurrentHitPoints() > getMaxHitPoints()) {
            setCurrentHitPoints(getMaxHitPoints());
        }

        for (InventoryItem item : getInventory()) {
            if (item.getDetails().getID() == potion.getID()) {
                item.setQuantity(item.getQuantity() - 1);
                break;
            }
        }

        World.sendMessengerObserverNotification("message", "You drink a " + potion.getName() + "\n");

        monsterAttack();
    }

    private void monsterAttack() {
        int damageToPlayer = DiceRoller.rollDice(1, World.getCurrentMonster().getMaxDamage(), 0);

        World.sendMessengerObserverNotification("message", "The "
                + World.getCurrentMonster().getName() + " did " + damageToPlayer + " points of damage.\n");

        setCurrentHitPoints(getCurrentHitPoints() - damageToPlayer);

        if (getCurrentHitPoints() <= 0) {
            World.sendMessengerObserverNotification("message", "The "
                    + World.getCurrentMonster().getName() + " killed you.\n");

            moveHome();
        }
    }

    public void moveHome() {
        LocationLogic.moveToLocation(World.LocationByID(0));
    }
}
