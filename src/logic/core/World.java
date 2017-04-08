package logic.core;

import logic.entity.Monster;
import logic.entity.Player;
import logic.enums.ItemID;
import logic.item.Item;
import logic.observer.GameObserver;
import logic.observer.MessageObserver;
import logic.quests.Quest;
import ui.GameUI;

import java.util.ArrayList;
import java.util.List;

public class World {
    private static List<Item> items = new ArrayList<>();
    private static List<Monster> monsters = new ArrayList<>();
    private static List<Quest> quests = new ArrayList<>();
    private static List<Location> locations = new ArrayList<>();

    private static Player _player;
    private static Monster _currentMonster;

    private static List<GameObserver> observerList = new ArrayList<>();
    private static List<MessageObserver> messageObservers = new ArrayList<>();

    private World() {

    }

    public static void init(GameUI ui) {
        SaveSystem.init();
        LoadSystem.init();

        items = LoadSystem.loadItemList();
        monsters = LoadSystem.loadMonsters();
        quests = LoadSystem.loadQuests();
        locations = LoadSystem.loadLocations();

        _player = LoadSystem.loadPlayer();
        ui.init();

        _player.moveHome();
        _player.addItemToInventory(World.ItemByID(ItemID.RUSTY_SWORD.getValue()));
    }

    private static void ensureObserverListCreation() {
        if (observerList == null) {
            observerList = new ArrayList<>();
        }

        if (messageObservers == null) {
            messageObservers = new ArrayList<>();
        }
    }

    public static Monster getCurrentMonster() {
        return _currentMonster;
    }

    public static void setCurrentMonster(Monster monster) {
        _currentMonster = monster;
    }

    public static void addObserverToList(GameObserver observer) {
        ensureObserverListCreation();
        observerList.add(observer);
    }

    public static void addMessengerObserver(MessageObserver observer) {
        ensureObserverListCreation();
        messageObservers.add(observer);
    }

    public static void removeObserverFromList(GameObserver observer) {
        ensureObserverListCreation();
        observerList.remove(observer);
    }

    public static void removeMessengerObserver(MessageObserver observer) {
        ensureObserverListCreation();
        messageObservers.remove(observer);
    }

    public static void sendObserverNotification(String message) {
        for (GameObserver observer : observerList) {
            observer.sendNotification(message);
        }
    }

    public static void sendMessengerObserverNotification(String type, String message) {
        for (MessageObserver mo : messageObservers) {
            mo.sendMessage(type, message);
        }
    }

    public static Player getPlayer() {
        return _player;
    }

    public static Item ItemByID(Integer id) {
        if(id != null){
            return items.get(id);
        }

        return null;
    }

    public static Monster MonsterByID(Integer id) {
        if(id != null){
            return monsters.get(id);
        }

        return null;
    }

    public static Quest QuestByID(Integer id) {
        if(id != null){
            return quests.get(id);
        }

        return null;
    }

    public static Location LocationByID(Integer id) {
        if(id != null){
            return locations.get(id);
        }

        return null;
    }
}
