package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Alien implements Shootable {

    private boolean visible;
    private int speed;
    private Picture representation;


    @Override
    public int getSpeed() {
        return speed;
    }

    public Alien(int x, int y, int speed) {
        this.speed = speed;
        double ran = Math.random();
        if (ran < .4) {
            representation = new Picture(x, y, "Resources/invaderRed.png");
        }
        if (ran >= .4 && ran < .7) {
            representation = new Picture(x, y, "Resources/invaderBlue.png");
        }
        if (ran >= .7) {
            representation = new Picture(x, y, "Resources/invaderGreen.png");
        }
        representation.draw();
        visible=true;
    }

    @Override
    public void shoot(Shootable[] caracters) {
        Bullet bullet = new Bullet(this);
        bullet.shootDownwards();
    }

    public void kill() {
        visible = false;
        representation.delete();
        Explosion.explode(this);
    }

    @Override
    public void hit() {
        kill();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }


    public void moveDown() {
        representation.translate(0, getSpeed() * 2);
    }

    public void moveRight() {
        if (getX() + getWidth() < Field.getWIDTH()) {
            representation.translate(speed, 0);
        }
    }

    public void moveLeft() {
        if (getX() > Field.getPADDING()) {
            representation.translate(-speed, 0);
        }
    }

    public int getX() {
        return representation.getX();
    }

    public int getY() {
        return representation.getY();
    }

    public int getWidth() {
        return representation.getWidth();
    }

    public int getHeight() {
        return representation.getHeight();
    }

    public  static void moveHorde(Alien[] horde){

    /*

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
*/


        while (horde[horde.length-1].getY() <= Field.getHEIGHT() * .8) {
            while (horde[0].getX() + horde[0].getWidth() < Field.getWIDTH()-Field.getPADDING()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < horde.length; i++) {
                    horde[i].moveRight();
                }
            }
            for (Alien a : horde) {
                a.moveDown();
            }
            while (horde[0].getX() > Field.getPADDING() + horde[0].getWidth()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < horde.length; i++) {
                    horde[i].moveLeft();
                }
            }
            for (Alien a : horde) {
                a.moveDown();
            }
        }
    }











    @Override
    public String toString() {
        return "Alien " + "X: " + getX() + " Y: " + getY();
    }
}

