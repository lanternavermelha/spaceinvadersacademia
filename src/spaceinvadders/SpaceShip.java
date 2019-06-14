package spaceinvadders;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SpaceShip implements Shootable {

    private boolean visible;
    private Picture ship;
    private int speed;
    private int playerLevel;



    private int aliensKilled;

    public SpaceShip(int x, int y, int speed) {
        ship = new Picture(Field.getWIDTH() / 2, Field.getHEIGHT() - 100, "Resources/ship.png");
        ship.draw();
        this.speed = 10;//speed;
        visible=true;
    }

    public int getX() {
        return ship.getX();
    }

    public int getY() {
        return ship.getY();
    }

    public int getWidth() {
        return ship.getWidth();
    }

    public int getHeight() {
        return ship.getHeight();
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public int getAliensKilled() {
        return aliensKilled;
    }

    public void moveRight() {
        if (getX() + getWidth() < Field.getWIDTH()) {
            ship.translate(speed, 0);
        }
    }

    public void moveLeft() {
        if (getX() > Field.getPADDING()) {
            ship.translate(-speed, 0);
        }
    }

    @Override
    public void hit() {
        Field.play("/Users/albertoreis/dev/spaceinvadersgroup/resources/explosion.wav");
        kill();
    }

    @Override
    public void shoot(Shootable[] gameobjects) {
        Bullet bullet = new Bullet(this);
        bullet.shootUpwards(gameobjects);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }


    @Override
    public void kill() {
        ship.delete();
        Explosion.explode(this);
        visible = false;
    }

    @Override
    public String toString() {
        return "SpaceShip";
    }
}