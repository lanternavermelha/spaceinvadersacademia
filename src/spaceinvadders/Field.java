package spaceinvadders;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Field {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDING = 10;

    private static int score;
    private Shootable[] gameObjects;
    private Alien[] aliens;
    private ProtectionBlock[] blocks;
    private Keyboard keyboard;
    private SpaceShip spaceShip;
    private Picture spaceCanvas;
    private Boss[] bosses;
    private GameLevel gameLevel;
    private UserInputHandler userInputHandler = new UserInputHandler();

    public Field(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
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


    //PLAYSOUNDS
//TODO maybe a class latter

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

    public void levelChecker(Shootable[] gameObjects) {
        for (Shootable gameObj : gameObjects) {
            if (!(gameObj instanceof SpaceShip)) {

            }
        }
    }

    public void init() {
        //play("resources/8bit_Trisco.wav");
        createCanvas();
        userInputHandler.keyboardInit();

        gameObjects = GameObjectsFactory.createCharacters(gameLevel);

        spaceShip = (SpaceShip) gameObjects[gameObjects.length - 1];
        bosses = selectHordeBoss(gameObjects);
//        System.out.println("Bosses: " + bosses.length);
        aliens = selectHorde(gameObjects);
//        System.out.println("Aliens: " + aliens.length);
        blocks = selectHordeProtectionBlock(gameObjects);
//        System.out.println("Blocks: " + blocks.length);

        //Alien.moveHorde(aliens);
    }

    //TODO maybe a class latter
    public void createCanvas() {
        switch (gameLevel) {
            case ROOKIE:
                spaceCanvas = new Picture(PADDING, PADDING, "resources/canvas/rookiecanvas.png");
                break;
            case INTERMEDIATE:
                spaceCanvas = new Picture(PADDING, PADDING, "resources/canvas/intermediatecanvas.png");
                break;
            case PRO:
                spaceCanvas = new Picture(PADDING, PADDING, "resources/canvas/procanvas.png");
                break;
            case INSANE:
                spaceCanvas = new Picture(PADDING, PADDING, "resources/canvas/insanecanvas.png");
                break;
        }
        spaceCanvas.draw();
    }

    //for debuging
    private ProtectionBlock[] selectHordeProtectionBlock(Shootable[] gameObjects) {
        int protectionAmount = 0;

        for (Shootable block : gameObjects
        ) {
            if (block instanceof ProtectionBlock) {
                protectionAmount++;
            }
        }
        ProtectionBlock[] result = new ProtectionBlock[protectionAmount];

        for (int i = 0; i < result.length; i++) {
            for (Shootable block : gameObjects
            ) {
                if (block instanceof ProtectionBlock) {
                    result[i] = (ProtectionBlock) block;
                }
            }
        }
        return result;
    }

    //for debuging
    private Boss[] selectHordeBoss(Shootable[] gameObjects) {
        int protectionAmount = 0;

        for (Shootable boss : gameObjects
        ) {
            if (boss instanceof Boss) {
                protectionAmount++;
            }
        }
        Boss[] result = new Boss[protectionAmount];

        for (int i = 0; i < result.length; i++) {
            for (Shootable boss : gameObjects
            ) {
                if (boss instanceof Boss) {
                    result[i] = (Boss) boss;
                }
            }
        }
        return result;
    }

    //for debuging
    private Alien[] selectHorde(Shootable[] gameObjects) {
        int aliensAmount = 0;

        for (Shootable alien : gameObjects
        ) {
            if (alien instanceof Alien) {
                aliensAmount++;
            }
        }
        Alien[] result = new Alien[aliensAmount];
        for (int i = 0; i < result.length; i++) {
            result[i] = (Alien) gameObjects[i];
        }
        return result;
    }

    private class UserInputHandler implements KeyboardHandler {
        private void keyboardInit() {
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
                play("resources/zap.wav");
                spaceShip.shoot(gameObjects);
            }
            if (keyboardEvent.getKey() == keyboardEvent.KEY_DOWN) {
                //bosses.shoot(gameObjects);
            }

        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {
        }
    }


}