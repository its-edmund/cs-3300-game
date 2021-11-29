package core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum SongEnum {
    BACKGROUND1("Jason Shaw - SOLO ACOUSTIC GUITAR.mp3", 0.02);

    private String filename;
    private double volume;

    // Class vars
    private static final Random RANDOM = new Random();
    private static final List<SongEnum> VALUES
            = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    private SongEnum(String filename, double volume) {
        this.filename = filename;
        this.volume = volume;
    }

    public String getFilepath() {
        return AppPaths.SOUND_PATH + filename;
    }

    public static SongEnum getRandomSongEnum() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public double getVolume() {
        return volume;
    }
}
