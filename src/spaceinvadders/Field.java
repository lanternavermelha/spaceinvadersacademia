package spaceinvadders;

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


public class Field implements KeyboardHandler {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDING = 10;
    private static int DELAY;

    private static final int MOVE_UNIT = 20;
    private int score;
    private Shootable[] gameObjects;
    private Alien[] aliens;
    private ArrayList<ProtectionBlock> blocks;
    private Keyboard keyboard;
    private SpaceShip spaceShip;
    private Picture spaceCanvas;
    private ArrayList<Boss> boss = null;
    private Boss b;
    private Alien[] ali;
    private GameLevel gameLevel;

    public Field(int delay, GameLevel gameLevel) {
        this.DELAY = delay;
        this.gameLevel = gameLevel;

    }

    //maybe a class latter
    public void createCanvas() {
        switch (gameLevel) {
            case ROOKIE:
                spaceCanvas = new Picture(PADDING, PADDING, "/Users/albertoreis/dev/spaceinvadersgroup/resources/canvas/rookiecanvas.png");
                break;
            case INTERMEDIATE:
                spaceCanvas = new Picture(PADDING, PADDING, "/Users/albertoreis/dev/spaceinvadersgroup/resources/canvas/intermediatecanvas.png");
                break;
            case PRO:
                spaceCanvas = new Picture(PADDING, PADDING, "/Users/albertoreis/dev/spaceinvadersgroup/resources/canvas/procanvas.png");
                break;
            case INSANE:
                spaceCanvas = new Picture(PADDING, PADDING, "/Users/albertoreis/dev/spaceinvadersgroup/resources/canvas/insanecanvas.png");
                break;
        }
        spaceCanvas.draw();
    }

    public void init() {
        //Theme Music
       // play("/Users/albertoreis/dev/spaceinvadersgroup/resources/8bit_Trisco.wav");

        createCanvas();

        keyboardInit();

        gameObjects = GameObjectsFactory.createCharacters(gameLevel);

        spaceShip = (SpaceShip) gameObjects[gameObjects.length - 1];

        b = (Boss) gameObjects[gameObjects.length - 2];

       //Alien.moveHorde(aliens);
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
            spaceShip.shoot(gameObjects);
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_DOWN) {
            b.shoot(gameObjects);
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