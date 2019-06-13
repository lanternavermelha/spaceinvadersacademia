package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Alien extends Character {

    private boolean dead;
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
    }

    @Override
    public void shoot(Character[] caracters) {
        Bullet bullet = new Bullet(this);
        bullet.shootDownwards();
    }

    public void kill() {
        dead = true;
        representation.delete();
    }

    public boolean isDead() {
        return dead;
    }

    public void moveDown() {
        representation.translate(0, getSpeed() * 2);

    }

    public void moveRight() {
        representation.translate(getSpeed(), 0);
    }

    public void moveLeft() {
        representation.translate(-getSpeed(), 0);
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


    @Override
    public String toString() {
        return "Alien " + "X: " + getX() + " Y: " + getY();
    }
}

