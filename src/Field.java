import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class Field implements KeyboardHandler {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDING = 10;
    private static int DELAY;

    private static final int MOVE_UNIT = 20;
    private int score;
    private Alien[] aliens;
    private Keyboard keyboard;
    private Player player;
    private Rectangle gameCanvas;

    private Text gameover = new Text(250, 250, " YOUUUUUU LOOOOOOOOSSSSSSEEEE");


    public Field(int delay) {

        play("/Users/albertoreis/Downloads/academiaProject/resources/8bit_Trisco.wav");
        //Creates a field where the player and aliens will be displayed
        gameCanvas = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        gameCanvas.setColor(Color.BLACK);
        gameCanvas.fill();
        this.DELAY = delay;
        keyboardInit();
    }

    public void init() {
        player = new Player(WIDTH / 2, HEIGHT - 20, 40);

        while(player.getPlayerLevel() != 3) {

            if (player.getPlayerLevel() == 0) {
                Alien[] aliens = createHordeOfAliens(2, 15);
                //Move all aliens if they aren't dead
                moveAliens(aliens);
            }

            if (player.getPlayerLevel() == 1) {
                Alien[] aliens = createHordeOfAliens(2, 20);
                //Move all aliens if they aren't dead
                moveAliens(aliens);
            }
            if(player.getPlayerLevel() == 2){
                Boss boss = new Boss((WIDTH/2)- 40,PADDING*2,2);

            }

        }
    }


    //PLAYSOUNDS
    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    /**
     * Creates a bunch of aliens
     */

    public Alien[] createHordeOfAliens(int speed, int hordeSize) {

        aliens = new Alien[hordeSize];

        int positionX = WIDTH / 3;
        int positionY = PADDING *2;

        for (int i = 0; i < aliens.length; i++) {

            aliens[i] = new Alien(positionX, positionY, speed);
            positionX += 70;
            if (i == 4 || i == 9 || i == 14) { //5 by 5
                positionX = WIDTH / 3;
                positionY = positionY + 50;
            }
        }

        return aliens;
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
        //Enquanto o ultimo alien do array nao chegar à border

        while (aliens[aliens.length - 1].representation.getY() <= HEIGHT * .8) {

            while (updateAlienReferenceRight(aliens).representation.getX() + aliens[0].representation.getWidth() < WIDTH - PADDING) {
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
                a.moveDown();
            }
            while (updateAlienReferenceLeft(aliens).representation.getX() > PADDING + aliens[0].representation.getWidth()) {
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
        if ((keyboardEvent.getKey() == keyboardEvent.KEY_RIGHT)
                && player.ship.getX() + player.ship.getWidth() < WIDTH) {
            player.moveRight();
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_LEFT && player.ship.getX() > 20) {
            player.moveLeft();
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_UP && player.ship.getY() + player.ship.getWidth() > 20) {
            //player.ship.translate(0, -10);

        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_DOWN && player.ship.getY() < HEIGHT) {
            //player.ship.translate(0, 10);
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_SPACE) {
            play("/Users/albertoreis/Downloads/academiaProject/resources/bulletsound.wav");
            player.shoot();
            player.hitChecker(aliens);
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