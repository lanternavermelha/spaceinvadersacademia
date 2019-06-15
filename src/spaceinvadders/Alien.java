package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Alien extends BadGuys {

    private boolean active;
    private int speed;
    private Picture representation;

    public Alien(int x, int y, GameLevel gameLevel) {
        //TODO switch to change speed and shooting pattern

        speed = speed;

        double ran = Math.random();
        if (ran < .4) {
            representation = new Picture(x, y, "resources/invaderRed.png");
        }
        if (ran >= .4 && ran < .7) {
            representation = new Picture(x, y, "resources/invaderBlue.png");
        }
        if (ran >= .7) {
            representation = new Picture(x, y, "resources/invaderGreen.png");
        }
        representation.draw();
        active = true;
    }

    public static void moveHorde(Alien[] horde) {

    /*

    public Alien updateAlienReferenceRight(Alien[] aliens) {
        Alien reference = aliens[aliens.length - 1];
        if (aliens[14].isActive() && aliens[4].isActive() && aliens[9].isActive()) {
            reference = aliens[13];
            if (aliens[13].isActive() && aliens[8].isActive() && aliens[3].isActive()) {
                reference = aliens[12];
                if (aliens[12].isActive() && aliens[7].isActive() && aliens[2].isActive()) {
                    reference = aliens[11];
                    if (aliens[6].isActive() && aliens[6].isActive() && aliens[1].isActive()) {
                        reference = aliens[10];
                    }
                }
            }
        }
        return reference;
    }

    public Alien updateAlienReferenceLeft(Alien[] aliens) {
        Alien reference = aliens[0];
        if (aliens[0].isActive() && aliens[5].isActive() && aliens[10].isActive()) {
            reference = aliens[1];
            if (aliens[1].isActive() && aliens[6].isActive() && aliens[11].isActive()) {
                reference = aliens[2];
                if (aliens[2].isActive() && aliens[7].isActive() && aliens[12].isActive()) {
                    reference = aliens[3];
                }
            }
        }
        return reference;
    }
*/

        while (horde[horde.length - 1].getY() <= Field.getHEIGHT() * .8) {
            while (horde[0].getX() + horde[0].getWidth() < Field.getWIDTH() - Field.getPADDING()) {
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
    public int getSpeed() {
        return speed;
    }

    @Override
    public void shoot(Shootable[] characters) {
        Bullet bullet = new Bullet(this);
        bullet.shootDownwards();
    }

    @Override
    public void hit() {
        kill();
    }

    @Override
    public void kill() {
        active = false;
        representation.delete();
        Explosion.explode(this);
    }

    @Override
    public boolean isActive() {
        return active;
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

    public void moveDown() {
        representation.translate(0, getHeight() * 2);
    }


    @Override
    public String toString() {
        return "Alien " + "X: " + getX() + " Y: " + getY();
    }
}

