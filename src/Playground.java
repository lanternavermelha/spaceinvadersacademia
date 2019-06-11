import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;


public class Playground implements KeyboardHandler {



    Rectangle rec = new Rectangle();
     int HEIGHT = 600;
    int WIDTH = 800;
    Picture background = new Picture(10, 10, "/Users/albertoreis/dev/simplegfxtesting/resources/background.jpg");
    Rectangle ship = new Rectangle(400, 550, 20,20);
    Rectangle shipBullets;


    public void init() {
       // background.draw();
        ship.setColor(Color.RED);
        ship.fill();

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
        //SPACE KEY (START THE GAME)
        KeyboardEvent keyboardSpace = new KeyboardEvent();
        keyboardSpace.setKey(KeyboardEvent.KEY_SPACE);
        keyboardSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardSpace);




    }

    public void moveUp(Rectangle shipBullets)  {




            for (int y = shipBullets.getY(); y > 10; y--) {
                shipBullets.translate(0, -20);
                shipBullets.fill();
                System.out.println("AAAA");
            }
    }

    public void timeDelay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == keyboardEvent.KEY_SPACE) {
            shipBullets = new Rectangle(ship.getX(), ship.getY(), 5,15);
            shipBullets.setColor(Color.GREEN);
            shipBullets.fill();
            System.out.println("aqui");
            for (int y = shipBullets.getY(); y > 10; y--) {
                shipBullets.fill();
                shipBullets.delete();
                System.out.println("aqui111");

                shipBullets.translate(0, -2);

            }
            System.out.println("aqui11111");

            shipBullets.fill();
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_RIGHT) {
            ship.translate(10,0);
        }
        if (keyboardEvent.getKey() == keyboardEvent.KEY_LEFT) {
            ship.translate(-10,0);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}


// rec.delete();//Hide
//rec.translate(x,y); //Moving - (players only x)
/*
package org.academiadecodigo.bootcamp.graphicshandson;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Teste implements KeyboardHandler{

   Rectangle spaceShip = new Rectangle(0,0, 100,100);
   Picture picture = new Picture(0,0,"maxresdefault.jpg");


   public void test(){
       Rectangle spaceShip = new Rectangle(0,0, 100,100);
       spaceShip.draw();
       spaceShip.setColor(Color.GREEN);
       spaceShip.fill();

       Rectangle rectangle1 = new Rectangle(2,2,95,95);
       rectangle1.draw();
       rectangle1.setColor(Color.YELLOW);
       //rectangle1.fill();

       Picture picture = new Picture(0,0,"maxresdefault.jpg");
       picture.draw();

       Keyboard keyboard = new Keyboard(this);
       KeyboardEvent event = new KeyboardEvent();
       event.setKey(KeyboardEvent.KEY_SPACE);
       event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
       keyboard.addEventListener(event);

       KeyboardEvent eventLettereD = new KeyboardEvent();
       eventLettereD.setKey(KeyboardEvent.KEY_D);
       eventLettereD.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
       keyboard.addEventListener(eventLettereD);
   }





   @Override
   public void keyPressed(KeyboardEvent keyboardEvent) {
       if(keyboardEvent.getKey() == keyboardEvent.KEY_SPACE){
           spaceShip.fill();
           spaceShip.grow(10,10);
       }else{
           picture.translate(5,10);
       }
   }

   @Override
   public void keyReleased(KeyboardEvent keyboardEvent) {

   }


}


 */



