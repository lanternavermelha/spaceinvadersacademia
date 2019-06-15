package spaceinvadders;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class AudioPlay {
    private Clip clip;
    private String path;

    public AudioPlay(String path) {
        this.path = path;
    }

    public void runAudio() {
        try {
            ClassLoader classLoader = AudioPlay.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(path);
            clip = AudioSystem.getClip();

            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));

            clip.open(ais);
            clip.start();
        } catch (Exception ex) {
            System.out.println("NOT");
        }
    }

    public void stopAudio() {
        clip.close();
    }

}