package spaceinvadders;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class Field implements KeyboardHandler {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDING = 10;
    private static int DELAY;

    private static final int MOVE_UNIT = 20;
    private int score;
    private Character[] gameObjects;
    private ArrayList<Alien> aliens;
    private ArrayList<ProtectionBlock> blocks;
    private Keyboard keyboard;
    private SpaceShip spaceShip;
    private Rectangle gameCanvas;
    private Picture spaceCanvas;
    private ArrayList<Boss> boss = null;
    private Boss b;
    private Alien[] ali;

    public Field(int delay) {
        play("/Users/albertoreis/dev/spaceinvadersgroup/resources/8bit_Trisco.wav");
        createCanvas();
        this.DELAY = delay;
        keyboardInit();
    }

    //maybe a class latter
    public void createCanvas() {
        spaceCanvas = new Picture(PADDING, PADDING, "/Users/albertoreis/dev/spaceinvadersgroup/resources/spacecanvas.png");
        spaceCanvas.draw();
        //gameCanvas = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        //gameCanvas.setColor(Color.BLACK);
        //gameCanvas.fill();
    }

    public void init() {

        gameObjects = GameObjectsFactory.createCharacters(GameLevel.INSANE);

        spaceShip = (SpaceShip) gameObjects[gameObjects.length - 1];

        b = (Boss) gameObjects[gameObjects.length - 2];

        for (Character a : gameObjects) {
            if (a instanceof Alien) {
                System.out.println(a);
            }
        }
        System.out.println(Arrays.toString(ali));

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {


                }
            }
        });
        t1.start();
    }

    //PLAYSOUNDS

    /**
     * Method that uses javax.sound to stream audio and java.io to read from a file
     *
     * @param filename
     */
    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }


    public Alien updateAlienReferenceRight(Alien[] aliens) {
        Alien reference = aliens[aliens.length - 1];
        if (aliens[14].isDead() && aliens[4].isDead() && aliens[9].isDead()) {
            reference = aliens[13];
            if (aliens[13].isDead() && aliens[8].isDead() && aliens[3].isDead()) {
                reference = aliens[12];
                if (aliens[12].isDead() && aliens[7].isDead() && aliens[2].isDead()) {
                    reference = aliens[11];
                    if (aliens[6].isDead() && aliens[6].isDead() && aliens[1].isDead()) {
                        reference = aliens[10];
                    }
                }
            }
        }
        return reference;
    }

    public Alien updateAlienReferenceLeft(Alien[] aliens) {
        Alien reference = aliens[0];
        if (aliens[0].isDead() && aliens[5].isDead() && aliens[10].isDead()) {
            reference = aliens[1];
            if (aliens[1].isDead() && aliens[6].isDead() && aliens[11].isDead()) {
                reference = aliens[2];
                if (aliens[2].isDead() && aliens[7].isDead() && aliens[12].isDead()) {
                    reference = aliens[3];
                }
            }
        }
        return reference;
    }

    public void moveAliens(Alien[] aliens) {

        while (aliens[14].getY() <= HEIGHT * .8) {

            while (updateAlienReferenceRight(aliens).getX() + aliens[0].getWidth() < WIDTH - PADDING) {
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < aliens.length; i++) {
                    aliens[i].moveRight();
                }
            }
            for (Alien a : aliens) {
                //a.moveDown();
            }
            while (updateAlienReferenceLeft(aliens).getX() > PADDING + aliens[0].getWidth()) {
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < aliens.length; i++) {
                    aliens[i].moveLeft();
                }
            }
            for (Alien a : aliens) {
                a.moveDown();
            }
        }
    }

    public void keyboardInit() {
        keyboard = new Keyboard(this);
        //RIGHT KEY
        KeyboardEvent keyboardRight = new KeyboardEvent();
        keyboardRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyboardRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardRight);
        //LEFT KEY
        KeyboardEvent keyboardLeft = new KeyboardEvent();
        keyboardLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyboardLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardLeft);
        //UP KEY
        KeyboardEvent keyboardUp = new KeyboardEvent();
        keyboardUp.setKey(KeyboardEvent.KEY_UP);
        keyboardUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardUp);
        //LEFT KEY
        KeyboardEvent keyboardDown = new KeyboardEvent();
        keyboardDown.setKey(KeyboardEvent.KEY_DOWN);
        keyboardDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardDown);
        //S KEY (START THE GAME)
        KeyboardEvent keyboardS = new KeyboardEvent();
        keyboardS.setKey(KeyboardEvent.KEY_S);
        keyboardS.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardS);
        //SPACE KEY (START THE GAME)
        KeyboardEvent keyboardSpace = new KeyboardEvent();
        keyboardSpace.setKey(KeyboardEvent.KEY_SPACE);
        keyboardSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardSpace);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == keyboardEvent.KEY_RIGHT) {
            spaceShip.moveRight();
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_LEFT) {
            spaceShip.moveLeft();
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_SPACE) {
            play("/Users/albertoreis/dev/spaceinvadersgroup/resources/zap.wav");
            spaceShip.shoot();
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_DOWN) {
            b.shoot();
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getPADDING() {
        return PADDING;
    }

    public static int getMoveUnit() {
        return MOVE_UNIT;
    }

    public static int getDELAY() {
        return DELAY;
    }
}