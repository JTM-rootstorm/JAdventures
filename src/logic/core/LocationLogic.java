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

import logic.entity.Monster;
import logic.item.InventoryItem;
import logic.item.LootItem;
import logic.quests.QuestLogic;

public class LocationLogic {
    private LocationLogic(){

    }

    private static void moveToLocation(Location newLocation){
        if (!hasRequiredItemToEnter(newLocation)) {
            World.sendMessengerObserverNotification("message",
                    "\nYou must have a " + newLocation.getItemRequiredToEnter().getName()
                            + " to enter this location\n");
            return;
        }

        World.getPlayer().setCurrentLocation(newLocation);

        World.sendMessengerObserverNotification("location",
                "\n\n" + newLocation.getName() + "\n" + newLocation.getDescription());

        World.getPlayer().setCurrentHitPoints(World.getPlayer().getMaxHitPoints());

        if (newLocation.getQuestAvailableHere() != null) {
            if (QuestLogic.hasThisQuest(newLocation.getQuestAvailableHere(), World.getPlayer().getQuests())) {
                if (!QuestLogic.completedThisQuest(newLocation.getQuestAvailableHere(), World.getPlayer().getQuests())) {

                    if (QuestLogic.hasAllQuestCompletionItems(newLocation.getQuestAvailableHere(), World.getPlayer().getInventory())) {
                        QuestLogic.completeQuest(newLocation.getQuestAvailableHere());
                    }
                }
            } else {
                QuestLogic.giveQuestToPlayer(newLocation.getQuestAvailableHere());
            }
        }

        setMonsterForCurrentLocation(newLocation);

        World.sendObserverNotification("plr_move");
    }

    private static void setMonsterForCurrentLocation(Location location) {
        if (location.getMonsterLivingHere() != null) {
            World.sendMessengerObserverNotification("message", "You see a "
                    + location.getMonsterLivingHere().getName() + "\n");

            Monster standardMonster = World.MonsterByID(location.getMonsterLivingHere().getID());

            World.setCurrentMonster(new Monster(standardMonster.getID(), standardMonster.getName(),
                    standardMonster.getRewardExperiencePoints(), standardMonster.getRewardGold(),
                    standardMonster.getDicePoolList(), standardMonster.getStatArray()));

            for (LootItem li : standardMonster.getLootTable()) {
                World.getCurrentMonster().getLootTable().add(li);
            }
        } else {
            World.setCurrentMonster(null);
        }
    }

    public static void moveNorth() {
        Location location = World.LocationByID(World.getPlayer().getCurrentLocation().getID()).getLocationToNorth();
        if (location != null) {
            moveToLocation(location);
        }
    }

    public static void moveEast() {
        Location location = World.LocationByID(World.getPlayer().getCurrentLocation().getID()).getLocationToEast();
        if (location != null) {
            moveToLocation(location);
        }
    }

    public static void moveSouth() {
        Location location = World.LocationByID(World.getPlayer().getCurrentLocation().getID()).getLocationToSouth();
        if (location != null) {
            moveToLocation(location);
        }
    }

    public static void moveWest() {
        Location location = World.LocationByID(World.getPlayer().getCurrentLocation().getID()).getLocationToWest();
        if (location != null) {
            moveToLocation(location);
        }
    }

    static void moveToPlayerHome(){
        moveToLocation(World.LocationByID(0));
    }

    static void refreshLocation(){
        moveToLocation(World.getPlayer().getCurrentLocation());
    }

    private static Boolean hasRequiredItemToEnter(Location location) {
        if (location.getItemRequiredToEnter() == null) {
            return true;
        }

        for (InventoryItem item : World.getPlayer().getInventory()) {
            if (item.getDetails().getID() == location.getItemRequiredToEnter().getID()) {
                return true;
            }
        }

        return false;
    }
}
