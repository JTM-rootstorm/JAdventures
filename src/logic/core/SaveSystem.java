package logic.core;

import com.google.gson.Gson;
import logic.entity.Monster;
import logic.entity.Player;
import logic.item.Item;
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
    private static Gson gson = new Gson();
    private static String jarPathOnSystem;
    private static FileWriter saveFileWriter;

    private SaveSystem(){}

    static void init(){
        try {
            jarPathOnSystem = FilenameUtils.getPath(
                    URLDecoder.decode(GameRunner.class.getProtectionDomain().getCodeSource().getLocation().getPath(),
                            "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    static void savePlayer(Player player){
        String saveFilePath = jarPathOnSystem + "/save/" + player.getClass().getSimpleName() + ".json";
        try {
            saveFileWriter = new FileWriter(saveFilePath);

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

        for(Location location : locationList){
            saveFilePath = jarPathOnSystem + "/data/locations/" + location.getName() + ".json";
            saveFile = new File(saveFilePath);

            writeObjectToFile(saveFile, location);
        }
    }

    static void saveMonsterList(List<Monster> monsterList){
        String saveFilePath;
        File saveFile;

        for(Monster monster : monsterList){
            saveFilePath = jarPathOnSystem + "/data/monsters/" + monster.getName() + ".json";
            saveFile = new File(saveFilePath);

            writeObjectToFile(saveFile, monster);
        }
    }

    static void saveItemList(List<Item> itemList){
        String saveFilePath;
        File saveFile;

        for(Item item : itemList){
            saveFilePath = jarPathOnSystem + "/data/items/" + item.getName() + ".json";
            saveFile = new File(saveFilePath);

            writeObjectToFile(saveFile, item);
        }
    }

    static void saveQuestList(List<Quest> questList){
        String saveFilePath;
        File saveFile;

        for(Quest quest : questList){
            saveFilePath = jarPathOnSystem + "/data/quests/" + quest.getName() + ".json";
            saveFile = new File(saveFilePath);

            writeObjectToFile(saveFile, quest);
        }
    }

    private static void writeObjectToFile(File saveFile, Object objectToSave) {
        String jsonString;
        if(!saveFile.exists()){
            try{
                saveFileWriter = new FileWriter(saveFile);

                jsonString = gson.toJson(objectToSave);

                saveFileWriter.write(jsonString);
                saveFileWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
