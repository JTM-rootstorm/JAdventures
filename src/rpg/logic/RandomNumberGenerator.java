package rpg.logic;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomNumberGenerator {
    private RandomNumberGenerator(){

    }

    public static int NumberBetween(int minVal, int maxVal){
        return ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
    }
}
