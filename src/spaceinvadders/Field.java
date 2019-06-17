package spaceinvadders;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;


public class Field {

    private long lastFire;
    private long firingInterval = 300;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDING = 10;

    private boolean gameOver;

    private Picture canvas;
    private int score;
    private Shootable[] gameObjects;
    private Keyboard keyboard;
    private SpaceShip spaceShip;
    private GameLevel gameLevel;
    private UserInputHandler userInputHandler = new UserInputHandler();
    private ArrayList<Boss> bosses = new ArrayList<>();
    private AudioPlay backgroundmusic = new AudioPlay();

    private int bosscount = 0;

    static int getWIDTH() {
        return WIDTH;
    }

    public Field(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    static int getHEIGHT() {
        return HEIGHT;
    }

    static int getPADDING() {
        return PADDING;
    }

    void setBosscount(int i) {
        bosscount += i;
    }

    /**
     * Method to initiate the game
     */
    public void init() {

        backgroundmusic.backgroundMusic();

        canvas = createCanvas();

        userInputHandler.keyboardInit();

        gameObjects = GameObjectsFactory.createCharacters(gameLevel);

        spaceShip = (SpaceShip) gameObjects[gameObjects.length - 1];

        spaceShip.gimmeField(this);

        bossArray();

        AlienHorde.move(gameObjects);
        System.out.println("waiiiiit");

        bossDeploymentCheck();

        if (!gameOver) {
            for (Boss boss : bosses
            ) {
                boss.gimmeField(this);
                boss.gimmeTargets(gameObjects);
            }
        }

        while (!gameOver) {
            System.out.println(bosscount);
            if (bosscount == bosses.size()) {
                System.out.println("WIN");
                setGameWon();
            }
        }

    }

    void setGameWon() {
        this.gameOver = true;
        Picture gameover = new Picture(PADDING, PADDING, "resources/youwin.jpg");
        gameover.draw();
    }


    void setGameOver() {
        this.gameOver = true;
        Picture gameover = new Picture(PADDING, PADDING, "resources/GameOver.png");
        gameover.draw();
        backgroundmusic.killbackgroundmusic();
    }

    private void bossArray() {
        for (Shootable shootable : gameObjects
        ) {
            if (shootable instanceof Boss) {
                bosses.add((Boss) shootable);
            }
        }
    }


    /**
     * Method to create GameCanvas considering each game level
     */
    private Picture createCanvas() {
        Picture canvas = new Picture(PADDING, PADDING, "resources/canvas/rookiecanvas.png");

        switch (gameLevel) {

            case INTERMEDIATE:
                canvas.load("resources/canvas/intermediatecanvas.png");
                break;
            case PRO:
                canvas.load("resources/canvas/procanvas.png");
                break;
            case INSANE:
                canvas.load("resources/canvas/insanecanvas.png");
                break;
        }


        canvas.draw();
        return canvas;
    }

    /**
     * Method to check if Boss is Ready to deploy
     */

    private void bossDeploymentCheck() {

        for (Shootable gameObject : gameObjects
        ) {
            if (gameObject instanceof Boss) {
                if (!gameObject.isActive()) {
                    ((Boss) gameObject).activate();
                    score++;

                }
            }
        }
    }

    /**
     * InnerClass to handle KeyBoardActions
     */
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
                if (System.currentTimeMillis() - lastFire < firingInterval) {
                    return;
                }
                lastFire = System.currentTimeMillis();
                spaceShip.shoot(gameObjects);
            }
        }


        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }
    }

}