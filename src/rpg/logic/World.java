package rpg.logic;

import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.Nullable;
import rpg.logic.builders.LocationBuilder;
import rpg.logic.builders.QuestBuilder;
import rpg.logic.entity.Monster;
import rpg.logic.enums.ItemID;
import rpg.logic.enums.LocationID;
import rpg.logic.enums.MonsterID;
import rpg.logic.enums.QuestID;
import rpg.logic.item.HealingPotion;
import rpg.logic.item.Item;
import rpg.logic.item.LootItem;
import rpg.logic.item.weapon.Weapon;
import rpg.logic.quests.Quest;
import rpg.logic.quests.QuestCompletionItem;
import rpg.ui.GameUI;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    private static List<Item> items = new ArrayList<>();
    private static List<Monster> monsters = new ArrayList<>();
    private static List<Quest> quests = new ArrayList<>();
    private static List<Location> locations = new ArrayList<>();

    private static GameUI gameUI;

    private World(){

    }

    public static void init(){
        populateItems();
        populateMonsters();
        populateQuests();
        populateLocations();
    }

    private static void populateItems(){
        items.add(new Weapon(ItemID.RUSTY_SWORD, "Rusty Sword",
                "Rusty Swords", 0, 5));
        items.add(new Item(ItemID.RAT_TAIL, "Rat Tail", "Rat Tails"));
        items.add(new Item(ItemID.PIECE_OF_FUR, "Piece of Fur", "Pieces of Fur"));
        items.add(new Item(ItemID.SNAKE_FANG, "Snake Fang", "Snake Fangs"));
        items.add(new Item(ItemID.SNAKE_SKIN, "Snake Skin", "Snake Skins"));
        items.add(new Weapon(ItemID.CLUB, "Club", "Clubs", 3, 10));
        items.add(new HealingPotion(ItemID.HEALING_POTION, "Healing Potion",
                "Healing Potions", 5));
        items.add(new Item(ItemID.SPIDER_FANG, "Spider Fang", "Spider Fangs"));
        items.add(new Item(ItemID.SPIDER_SILK, "Spider Silk", "Spider Silks"));
        items.add(new Item(ItemID.ADVENTURER_PASS, "Adventurer Pass", "Adventurer Passes"));
    }

    private static void populateMonsters(){
        Monster rat = new Monster(MonsterID.RAT, "Rat", 5, 3,
                10, 3, 3);
        rat.getLootTable().add(new LootItem(ItemByID(ItemID.RAT_TAIL), 75, false));
        rat.getLootTable().add(new LootItem(ItemByID(ItemID.PIECE_OF_FUR), 75, true));

        Monster snake = new Monster(MonsterID.SNAKE, "Snake", 5,3,10,
                3,3);
        snake.getLootTable().add(new LootItem(ItemByID(ItemID.SNAKE_FANG), 75, false));
        snake.getLootTable().add(new LootItem(ItemByID(ItemID.SNAKE_SKIN), 75, true));

        Monster giantSpider = new Monster(MonsterID.GIANT_SPIDER, "Giant Spider", 20,
                5, 40, 10, 10);
        giantSpider.getLootTable().add(new LootItem(ItemByID(ItemID.SPIDER_FANG), 75, true));
        giantSpider.getLootTable().add(new LootItem(ItemByID(ItemID.SPIDER_SILK), 25, false));

        monsters.addAll(Arrays.asList(rat, snake, giantSpider));
    }

    private static void populateQuests(){
        Quest clearAlchemistGuarden = new QuestBuilder().id(QuestID.CLEAR_ALCHEMIST_GARDEN)
                    .name("Clear the Alchemist's Garden")
                    .description("Kill rats in the alchemist's garden and bring back 3 rat tails. You will receive a " +
                            "healing potion and 10 gold pieces.")
                    .rewardXP(20)
                    .rewardGold(10)
                .buildQuest();
        clearAlchemistGuarden.getQuestCompletionItems().add(new QuestCompletionItem(ItemByID(ItemID.RAT_TAIL), 3));
        clearAlchemistGuarden.setRewardItem(ItemByID(ItemID.HEALING_POTION));

        Quest clearFarmersField = new QuestBuilder().id(QuestID.CLEAR_FARMERS_FIELD)
                    .name("Clear the Farmer's Field")
                    .description("Kill snakes in the farmer's field and bring back 3 snake fangs. You will receive an " +
                            "adventurer's pass and 20 gold pieces.")
                    .rewardXP(20)
                    .rewardGold(20)
                .buildQuest();
        clearFarmersField.getQuestCompletionItems().add(new QuestCompletionItem(ItemByID(ItemID.SNAKE_FANG), 3));
        clearFarmersField.setRewardItem(ItemByID(ItemID.ADVENTURER_PASS));

        quests.addAll(Arrays.asList(clearAlchemistGuarden, clearFarmersField));
    }

    private static void populateLocations(){
        Location home = new LocationBuilder().id(LocationID.HOME)
                .name("Home")
                .description("It's your house.")
                .buildLocation();

        Location townSquare = new LocationBuilder().id(LocationID.TOWN_SQUARE)
                .name("Town Square")
                .description("You see a fountain")
                .buildLocation();

        Location alchemistHut = new LocationBuilder().id(LocationID.ALCHEMIST_HUT)
                .name("Alchemist's Hut")
                .description("There are many strange plants on the shelves.")
                .questAvailableHere(QuestByID(QuestID.CLEAR_ALCHEMIST_GARDEN))
                .buildLocation();

        Location alchemistGarden = new LocationBuilder().id(LocationID.ALCHEMISTS_GARDEN)
                .name("Alchemist's Garden")
                .description("Many plants are growing here.")
                .monsterLivingHere(MonsterByID(MonsterID.RAT))
                .buildLocation();

        Location farmhouse = new LocationBuilder().id(LocationID.FARMHOUSE)
                .name("Farmhouse")
                .description("There is a small farmhouse, with a farmer in front.")
                .questAvailableHere(QuestByID(QuestID.CLEAR_FARMERS_FIELD))
                .buildLocation();

        Location farmersField = new LocationBuilder().id(LocationID.FARM_FIELD)
                .name("Farmer's Field")
                .description("You see rows of vegetables growing here.")
                .monsterLivingHere(MonsterByID(MonsterID.SNAKE))
                .buildLocation();

        Location guardPost = new LocationBuilder().id(LocationID.GUARD_POST)
                .name("Guard Post")
                .description("There is a large, tough-looking guard here.")
                .itemRequiredToEnter(ItemByID(ItemID.ADVENTURER_PASS))
                .buildLocation();

        Location bridge = new LocationBuilder().id(LocationID.BRIDGE)
                .name("Bridge")
                .description("A stone bridge crosses a wide river.")
                .buildLocation();

        Location spiderField = new LocationBuilder().id(LocationID.SPIDER_FIELD)
                .name("Forest")
                .description("You see spider webs covering covering the trees in this forest.")
                .monsterLivingHere(MonsterByID(MonsterID.GIANT_SPIDER))
                .buildLocation();

        home.setLocationToNorth(townSquare);

        townSquare.setLocationToNorth(alchemistHut);
        townSquare.setLocationToSouth(home);
        townSquare.setLocationToEast(guardPost);
        townSquare.setLocationToWest(farmhouse);

        farmhouse.setLocationToEast(townSquare);
        farmhouse.setLocationToWest(farmersField);

        farmersField.setLocationToEast(farmhouse);

        alchemistHut.setLocationToSouth(townSquare);
        alchemistHut.setLocationToNorth(alchemistGarden);

        alchemistGarden.setLocationToSouth(alchemistHut);

        guardPost.setLocationToEast(bridge);
        guardPost.setLocationToWest(townSquare);

        bridge.setLocationToWest(guardPost);
        bridge.setLocationToEast(spiderField);

        spiderField.setLocationToWest(bridge);

        locations.addAll(Arrays.asList(home, townSquare, farmhouse, farmersField, alchemistHut, alchemistGarden,
                guardPost, bridge, spiderField));
    }

    public static void setGameUI(GameUI ui){
        gameUI = ui;
    }

    @Nullable
    public static Item ItemByID(ItemID id){
        for(Item item : items){
            if(item.getID() == id){
                return item;
            }
        }

        return null;
    }

    @Nullable
    public static Monster MonsterByID(MonsterID id){
        for(Monster monster : monsters){
            if(monster.getID() == id){
                return monster;
            }
        }

        return null;
    }

    @Nullable
    private static Quest QuestByID(QuestID id){
        for(Quest quest : quests){
            if(quest.getID() == id){
                return quest;
            }
        }

        return null;
    }

    @Nullable
    public static Location LocationByID(LocationID id){
        for(Location location : locations){
            if(location.getID() == id){
                return location;
            }
        }

        return null;
    }

    public static void saveObjectToJson(Object object, boolean debugFlag){
        Gson gson = new Gson();
        try {
            String path, teststr = "";
            if(!debugFlag){
                path = URLDecoder.decode(rpg.main.GameRunner.class.getProtectionDomain().getCodeSource()
                        .getLocation().getPath(), "UTF-8");
                path = FilenameUtils.getPath(path);
                path += object.getClass().getSimpleName() + ".json";
            }
            else{
                path = "/E:/IntelliJ Workspace/gamedev/rpg-game/" + object.getClass().getSimpleName() + ".json";
                teststr = URLDecoder.decode(rpg.main.GameRunner.class.getProtectionDomain().getCodeSource()
                        .getLocation().getPath(), "UTF-8");
                teststr = FilenameUtils.getPath(teststr);
                teststr += object.getClass().getSimpleName() + ".json";
            }

            FileWriter writer = new FileWriter(path);
            /*String json = gson.toJson(object);
            writer.write(json);*/
            writer.write(teststr);
            System.out.println(path);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
