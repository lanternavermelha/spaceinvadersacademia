
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;


public class Game implements KeyboardHandler {

    Rectangle rec = new Rectangle();
    int x = 1;
    int y = 1;
    int HEIGHT = 600;
    int WIDTH = 800;
    int delay = 200;

    //Picture ship = new Picture(400, 550, "/Users/albertoreis/dev/simplegfxtesting/resources/spaceship.png");
    //Picture background = new Picture(10, 10, "/Users/albertoreis/dev/simplegfxtesting/resources/background.jpg");

    Rectangle spaceShip = new Rectangle(WIDTH / 2, 550, 40, 40);

    Rectangle gameCanvas = new Rectangle(10, 10, WIDTH, HEIGHT);

    Text pressStart = new Text(WIDTH / 2, HEIGHT / 2, "PRESS S TO PLAY!!");


    //Rectangle enemy = new Rectangle(WIDTH / 2, HEIGHT / 4, 30, 30);
    //Rectangle[] enemyblock = new Rectangle[20];
    private Alien[] aliens=new Alien[20];


    public void start() {
        keyboardInit();
        //GAME CANVAS FORM
        gameCanvas.setColor(Color.BLACK);
        gameCanvas.fill();

        //background.draw();

        //PRESS START
        pressStart.setColor(Color.YELLOW);
        pressStart.grow(25, 15);
        pressStart.draw();

        //BLOCK OF ALIENS
        createBlock();
        spaceShip.setColor(Color.BLUE);
    }

    public void createBlock() {
        int positionY = 20; //pq 20??
        int positionX = WIDTH / 4;
        for (int i = 0; i < aliens.length; i++) {
            aliens[i] = new Alien(positionX, positionY, 10);
            positionX +=  50;
            if (i == 4||i==9||i==14) {
                positionX = WIDTH / 4;
                positionY = positionY + 50;
            }
        }
    }


    public void keyboardInit() {
        Keyboard keyboard = new Keyboard(this);
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

    /*


        public void moveUp() {
            if (enemy.getY() == HEIGHT - 60) {
                enemy.delete();
                return;
            }
            while (enemy.getX() > WIDTH - 40) {
                enemy.delete();
                enemy.translate(30, 0);
                enemy.draw();
            }
            enemy.translate(0, 1);
            while (enemy.getX() < 40) {
                enemy.delete();
                enemy.translate(-30, 0);
                enemy.draw();
            }
            moveUp();


            //       for (int y = shipBullets.getY(); y > 10; y--) {
            //         timeDelay(500);
            //       shipBullets.translate(0, -20);
            //     shipBullets.draw();
            //   System.out.println("AAAA");
            //}

        }
     */
    public void timeDelay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
        }
    }


    public void hitChecker(Alien[] enemyBlock, Rectangle bullet) {

        for (int i = enemyBlock.length-1; i >=0; i--) {
            if (!aliens[i].isDead()) {
                if ( bullet.getY() == enemyBlock[i].representation.getY() + 30//To get the bottom of the figure
                        || bullet.getX() <= enemyBlock[i].representation.getX() + enemyBlock[i].representation.getWidth()
                        && bullet.getX() >= enemyBlock[i].representation.getX()
                        ) {
                    enemyBlock[i].kill();
                    break;
                }
            }
        }

    }

    public void moveAliens() {
        //Enquanto o ultimo alien do array nao chegar à border

        while (aliens[aliens.length - 1].representation.getY() <= HEIGHT * .8) {

            while (aliens[aliens.length - 1].representation.getX() + 30 + 30 < WIDTH - 10) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (Alien a : aliens) {
                    a.moveRight();
                }
            }
            for (Alien a : aliens) {
                a.representation.translate(0, 20);
            }
            while (aliens[0].representation.getX() > 10 + 30) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < aliens.length; i++) {
                    aliens[i].representation.translate(-40, 0);
                }
            }
            for (Alien a : aliens) {
                a.representation.translate(0, 20);
            }
        }
    }







    public void bulletMove(Rectangle bullet) {
        while (bullet.getY() > 20) {
            bullet.translate(0, -20);
            //timeDelay(50);
        }
    }



    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == keyboardEvent.KEY_S) {
            pressStart.delete();
            for (int i = 0; i < aliens.length; i++) {
                aliens[i].representation.draw();
            }
            spaceShip.draw();
            //ship.draw();
        }


        if (keyboardEvent.getKey() == keyboardEvent.KEY_RIGHT && spaceShip.getX() + spaceShip.getWidth() < WIDTH) {
            spaceShip.translate(20, 0);
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_LEFT && spaceShip.getX() > 20) {
            spaceShip.translate(-20, 0);
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_UP && spaceShip.getY() + spaceShip.getWidth() > 20) {
            spaceShip.translate(0, -20);
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_DOWN && spaceShip.getY() < HEIGHT) {
            spaceShip.translate(0, 20);
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_SPACE) {
            Rectangle bullet = new Rectangle(spaceShip.getX(), spaceShip.getY(), 5, 10);
            bullet.setColor(Color.YELLOW);
            bullet.fill();
            //Picture shipBullets = new Picture(spaceShip.getX(), spaceShip.getY(), "/Users/albertoreis/dev/simplegfxtesting/resources/bullet.png");
            //shipBullets.draw();
            bulletMove(bullet);
            hitChecker(aliens, bullet);
            //moveUp();
        }
    }



    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == keyboardEvent.KEY_SPACE) {

        }
    }

}





