

package spaceinvadders;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class AudioPlay {

    private Clip backgroundmusic;
    private Clip zap;
    private Clip explosion;

    public void backgroundMusic() {
        try {
            ClassLoader classLoader = AudioPlay.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("resources/8bit_Trisco.wav");
            backgroundmusic = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
            backgroundmusic.open(ais);
            backgroundmusic.start();
        } catch (Exception ex) {
            System.out.println("background not");
        }
    }

    public void killbackgroundmusic() {
        backgroundmusic.close();


    }
    public void killzap() {
        zap.close();


    }
    public void zap() {
        try {

            ClassLoader classLoader = AudioPlay.class.getClassLoader();

            InputStream inputStream = classLoader.getResourceAsStream("resources/zap.wav");

            zap = AudioSystem.getClip();

            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));

            zap.open(ais);

            zap.start();
        } catch (Exception ex) {
            System.out.println("zap not");
        }
    }

    public void explosion() {
        try {
            ClassLoader classLoader = AudioPlay.class.getClassLoader();

            InputStream inputStream = classLoader.getResourceAsStream("resources/explosion.wav");
            explosion = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
            explosion.open(ais);
            explosion.start();
        } catch (Exception ex) {
            System.out.println("explosion not");
        }
    }

    private Clip bulletsound;

    public void bulletsound() {
        Clip bulletsound;
        try {
            ClassLoader classLoader = AudioPlay.class.getClassLoader();

            InputStream inputStream = classLoader.getResourceAsStream("resources/bulletsound.wav");
            bulletsound = AudioSystem.getClip();

            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));

            bulletsound.open(ais);
            bulletsound.start();

        } catch (Exception ex) {
            System.out.println("bulletsound not");
        }
    }


}


