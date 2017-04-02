package logic.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.entity.Monster;
import logic.entity.Player;
import logic.item.Item;
import logic.item.weapon.Weapon;
import logic.quests.Quest;
import main.GameRunner;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

class SaveSystem {
    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private static String jarPathOnSystem;
    private static String dataPath;
    private static FileWriter saveFileWriter;

    private SaveSystem(){}

    static void init(){
        try {
            jarPathOnSystem = FilenameUtils.getPath(
                    URLDecoder.decode(GameRunner.class.getProtectionDomain().getCodeSource().getLocation().getPath(),
                            "UTF-8"));

            dataPath = jarPathOnSystem + "/data/";

            File temp = new File(dataPath);
            temp.mkdir();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    static void savePlayer(Player player){
        String saveFilePath = jarPathOnSystem + "/save/";
        try {
            File save = new File(saveFilePath);
            save.mkdir();

            save = new File(saveFilePath + player.getClass().getSimpleName() + ".json");
            save.createNewFile();

            saveFileWriter = new FileWriter(save);

            String jsonString = gson.toJson(player);

            saveFileWriter.write(jsonString);
            saveFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveLocationList(List<Location> locationList){
        String saveFilePath;
        File saveFile;

        saveFilePath = dataPath + "/locations/";
        saveFile = new File(saveFilePath);
        saveFile.mkdir();

        for(Location location : locationList){
            saveFilePath = dataPath + "/locations/" + location.getID() + "_"
                    + location.getName().replaceAll(" ", "_") + ".json";
            saveFile = new File(saveFilePath);

            writeObjectToFile(saveFile, location);
        }
    }

    static void saveMonsterList(List<Monster> monsterList){
        String saveFilePath;
        File saveFile;

        saveFilePath = dataPath + "/monsters/";
        saveFile = new File(saveFilePath);
        saveFile.mkdir();

        for(Monster monster : monsterList){
            saveFilePath = dataPath + "/monsters/" + monster.getID() + "_"
                    + monster.getName().replaceAll(" ", "_") + ".json";
            saveFile = new File(saveFilePath);

            writeObjectToFile(saveFile, monster);
        }
    }

    static void saveItemList(List<Item> itemList){
        String saveFilePath;
        File saveFile;

        saveFilePath = dataPath + "/items/weapons/";
        saveFile = new File(saveFilePath);
        saveFile.mkdirs();

        for(Item item : itemList){
            if(item instanceof Weapon){
                saveFilePath = dataPath + "/items/weapons/" + item.getID() + "_"
                        + item.getName().replaceAll(" ", "_") + ".json";
            }
            else{
                saveFilePath = dataPath + "/items/" + item.getID() + "_"
                        + item.getName().replaceAll(" ", "_") + ".json";
            }
            saveFile = new File(saveFilePath);

            writeObjectToFile(saveFile, item);
        }
    }

    static void saveQuestList(List<Quest> questList){
        String saveFilePath;
        File saveFile;

        saveFilePath = dataPath + "/quests/";
        saveFile = new File(saveFilePath);
        saveFile.mkdir();

        for(Quest quest : questList){
            saveFilePath = dataPath + "/quests/" + quest.getID() + "_"
                    + quest.getName().replaceAll(" ", "_") + ".json";
            saveFile = new File(saveFilePath);

            writeObjectToFile(saveFile, quest);
        }
    }

    private static void writeObjectToFile(File saveFile, Object objectToSave) {
        String jsonString;
        try{
            saveFile.createNewFile();
            saveFileWriter = new FileWriter(saveFile);

            jsonString = gson.toJson(objectToSave);

            saveFileWriter.write(jsonString);
            saveFileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
