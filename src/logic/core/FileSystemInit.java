package logic.core;

import main.GameRunner;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class FileSystemInit {
    static String jarPathOnSystem;
    static String dataPath;

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
}
