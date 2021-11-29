package core;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;

public class GameMusic {

    SongEnum currentSongEnum;
    Media currentMedia;
    MediaPlayer currenMediaPlayer;

    public GameMusic() {

    }

    public void setSong(SongEnum songEnum) {

        currentSongEnum = songEnum;

        if (currenMediaPlayer != null) {
            stopCurrentSong();
        }

        currentMedia = new Media(new File(songEnum.getFilepath()).toURI().toString());
    }

    // DEBUG
    public void playCurrentSong() {
        currenMediaPlayer = new MediaPlayer(currentMedia);
        currenMediaPlayer.setVolume(currentSongEnum.getVolume());
        currenMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        currenMediaPlayer.play();
    }

    public void stopCurrentSong() {
        currenMediaPlayer.stop();
    }

    public void playBackgroundMusic() {

        if (currenMediaPlayer != null) {
            stopCurrentSong();
        }

        setSong(SongEnum.getRandomSongEnum());
        playCurrentSong();
    }


}
