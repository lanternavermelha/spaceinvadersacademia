package spaceinvadders;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.File;

public class Bullet {

    Rectangle representation;

    public Bullet(Character character) {
        representation = new Rectangle(character.getX() + (character.getWidth() / 2), character.getY()-character.getHeight()/2, 3, 8);
    }

    public void shootUpwards() {
        representation.setColor(Color.YELLOW);
        representation.fill();


        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (representation.getY()> Field.getPADDING()) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    representation.translate(0, -1);
                    System.out.println(representation.getX());
                    System.out.println(representation.getY());
                }
                representation.delete();
            }
        });

        t1.start();
    }
    public void shootDownwards() {
        representation.setColor(Color.YELLOW);
        representation.fill();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (representation.getY()+representation.getHeight()<Field.getHEIGHT()-Field.getPADDING()) {
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    representation.translate(0, 1);
                    System.out.println(representation.getX());
                    System.out.println(representation.getY());
                }
                representation.delete();
            }
        });

        t1.start();
    }

    public int getX() {
        return representation.getX();
    }


    public int getY() {
        return representation.getY();
    }

}
