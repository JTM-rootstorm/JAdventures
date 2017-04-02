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

        populateItems();
        populateMonsters();
        populateQuests();
        populateLocations();

        initPlayer();
        ui.init();

        _player.moveHome();
        _player.addItemToInventory(World.ItemByID(ItemID.RUSTY_SWORD.getValue()));
    }

    private static void initPlayer() {
        _player = LoadSystem.loadPlayer();
    }

    private static void populateItems() {
        /*items.add(new Weapon(ItemID.RUSTY_SWORD.getValue(), "Rusty Sword",
                "Rusty Swords", 0, 5));
        items.add(new Item(ItemID.RAT_TAIL.getValue(), "Rat Tail", "Rat Tails"));
        items.add(new Item(ItemID.PIECE_OF_FUR.getValue(), "Piece of Fur", "Pieces of Fur"));
        items.add(new Item(ItemID.SNAKE_FANG.getValue(), "Snake Fang", "Snake Fangs"));
        items.add(new Item(ItemID.SNAKE_SKIN.getValue(), "Snake Skin", "Snake Skins"));
        items.add(new Weapon(ItemID.CLUB.getValue(), "Club", "Clubs", 3, 10));
        items.add(new HealingPotion(ItemID.HEALING_POTION.getValue(), "Healing Potion",
                "Healing Potions", 5));
        items.add(new Item(ItemID.SPIDER_FANG.getValue(), "Spider Fang", "Spider Fangs"));
        items.add(new Item(ItemID.SPIDER_SILK.getValue(), "Spider Silk", "Spider Silks"));
        items.add(new Item(ItemID.ADVENTURER_PASS.getValue(), "Adventurer Pass", "Adventurer Passes"));*/

        items = LoadSystem.loadItemList();
    }

    private static void populateMonsters() {
        /*Monster rat = new Monster(MonsterID.RAT.getValue(), "Rat", 5, 3,
                10, 3, 3);
        rat.getLootTable().add(new LootItem(ItemID.RAT_TAIL.getValue(), 75, false));
        rat.getLootTable().add(new LootItem(ItemID.PIECE_OF_FUR.getValue(), 75, true));

        Monster snake = new Monster(MonsterID.SNAKE.getValue(), "Snake", 5, 3,
                10, 3, 3);
        snake.getLootTable().add(new LootItem(ItemID.SNAKE_FANG.getValue(), 75, false));
        snake.getLootTable().add(new LootItem(ItemID.SNAKE_SKIN.getValue(), 75, true));

        Monster giantSpider = new Monster(MonsterID.GIANT_SPIDER.getValue(), "Giant Spider", 20,
                5, 40, 10, 10);
        giantSpider.getLootTable().add(new LootItem(ItemID.SPIDER_FANG.getValue(), 75, true));
        giantSpider.getLootTable().add(new LootItem(ItemID.SPIDER_SILK.getValue(), 25, false));

        monsters.addAll(Arrays.asList(rat, snake, giantSpider));*/

        monsters = LoadSystem.loadMonsters();
    }

    private static void populateQuests() {
        /*Quest clearAlchemistGuarden = new QuestBuilder().id(QuestID.CLEAR_ALCHEMIST_GARDEN.getValue())
                .name("Clear the Alchemist's Garden")
                .description("Kill rats in the alchemist's garden and bring back 3 rat tails. You will receive a " +
                        "healing potion and 10 gold pieces.")
                .rewardXP(20)
                .rewardGold(10)
                .buildQuest();
        clearAlchemistGuarden.getQuestCompletionItems().add(new QuestCompletionItem(ItemID.RAT_TAIL.getValue(), 3));
        clearAlchemistGuarden.setRewardItem(ItemID.HEALING_POTION.getValue());

        Quest clearFarmersField = new QuestBuilder().id(QuestID.CLEAR_FARMERS_FIELD.getValue())
                .name("Clear the Farmer's Field")
                .description("Kill snakes in the farmer's field and bring back 3 snake fangs. You will receive an " +
                        "adventurer's pass and 20 gold pieces.")
                .rewardXP(20)
                .rewardGold(20)
                .buildQuest();
        clearFarmersField.getQuestCompletionItems().add(new QuestCompletionItem(ItemID.SNAKE_FANG.getValue(), 3));
        clearFarmersField.setRewardItem(ItemID.ADVENTURER_PASS.getValue());

        quests.addAll(Arrays.asList(clearAlchemistGuarden, clearFarmersField));*/

        quests = LoadSystem.loadQuests();
    }

    private static void populateLocations() {
        /*Location home = new LocationBuilder().id(LocationID.HOME.getValue())
                .name("Home")
                .description("It's your house.")
                .buildLocation();

        Location townSquare = new LocationBuilder().id(LocationID.TOWN_SQUARE.getValue())
                .name("Town Square")
                .description("You see a fountain")
                .buildLocation();

        Location alchemistHut = new LocationBuilder().id(LocationID.ALCHEMIST_HUT.getValue())
                .name("Alchemist's Hut")
                .description("There are many strange plants on the shelves.")
                .questAvailableHere(QuestID.CLEAR_ALCHEMIST_GARDEN.getValue())
                .buildLocation();

        Location alchemistGarden = new LocationBuilder().id(LocationID.ALCHEMISTS_GARDEN.getValue())
                .name("Alchemist's Garden")
                .description("Many plants are growing here.")
                .monsterLivingHere(MonsterID.RAT.getValue())
                .buildLocation();

        Location farmhouse = new LocationBuilder().id(LocationID.FARMHOUSE.getValue())
                .name("Farmhouse")
                .description("There is a small farmhouse, with a farmer in front.")
                .questAvailableHere(QuestID.CLEAR_FARMERS_FIELD.getValue())
                .buildLocation();

        Location farmersField = new LocationBuilder().id(LocationID.FARM_FIELD.getValue())
                .name("Farmer's Field")
                .description("You see rows of vegetables growing here.")
                .monsterLivingHere(MonsterID.SNAKE.getValue())
                .buildLocation();

        Location guardPost = new LocationBuilder().id(LocationID.GUARD_POST.getValue())
                .name("Guard Post")
                .description("There is a large, tough-looking guard here.")
                .itemRequiredToEnter(ItemID.ADVENTURER_PASS.getValue())
                .buildLocation();

        Location bridge = new LocationBuilder().id(LocationID.BRIDGE.getValue())
                .name("Bridge")
                .description("A stone bridge crosses a wide river.")
                .buildLocation();

        Location spiderField = new LocationBuilder().id(LocationID.SPIDER_FIELD.getValue())
                .name("Forest")
                .description("You see spider webs covering covering the trees in this forest.")
                .monsterLivingHere(MonsterID.GIANT_SPIDER.getValue())
                .buildLocation();

        home.setLocationToNorth(townSquare.getID());

        townSquare.setLocationToNorth(alchemistHut.getID());
        townSquare.setLocationToSouth(home.getID());
        townSquare.setLocationToEast(guardPost.getID());
        townSquare.setLocationToWest(farmhouse.getID());

        farmhouse.setLocationToEast(townSquare.getID());
        farmhouse.setLocationToWest(farmersField.getID());

        farmersField.setLocationToEast(farmhouse.getID());

        alchemistHut.setLocationToSouth(townSquare.getID());
        alchemistHut.setLocationToNorth(alchemistGarden.getID());

        alchemistGarden.setLocationToSouth(alchemistHut.getID());

        guardPost.setLocationToEast(bridge.getID());
        guardPost.setLocationToWest(townSquare.getID());

        bridge.setLocationToWest(guardPost.getID());
        bridge.setLocationToEast(spiderField.getID());

        spiderField.setLocationToWest(bridge.getID());

        locations.addAll(Arrays.asList(home, townSquare, farmhouse, farmersField, alchemistHut, alchemistGarden,
                guardPost, bridge, spiderField));*/

        locations = LoadSystem.loadLocations();
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
            for (Item item : items) {
                if (item.getID() == id) {
                    return item;
                }
            }
        }

        return null;
    }

    public static Monster MonsterByID(Integer id) {
        if(id != null){
            for (Monster monster : monsters) {
                if (monster.getID() == id) {
                    return monster;
                }
            }
        }

        return null;
    }

    public static Quest QuestByID(Integer id) {
        if(id != null){
            for (Quest quest : quests) {
                if (quest.getID() == id) {
                    return quest;
                }
            }
        }

        return null;
    }

    public static Location LocationByID(Integer id) {
        if(id != null){
            for (Location location : locations) {
                if (location.getID() == id) {
                    return location;
                }
            }
        }

        return null;
    }
}
