package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SpaceShip implements Shootable {

    private boolean active;
    private Picture ship;
    private int speed;
    private int playerLevel;
    private int aliensKilled;

    public SpaceShip(GameLevel gameLevel) {
        //TODO switch to change speed and shooting pattern
        System.out.println(55);
        ship = new Picture(Field.getWIDTH() / 2, Field.getHEIGHT() - 100, "resources/ship.png");
        System.out.println(56);
        ship.draw();
        System.out.println(57);
        this.speed = 10;
        System.out.println(58);
        active = true;
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
            ship.translate(10, 0);
        }
    }

    public void moveLeft() {
        if (getX() > Field.getPADDING()) {
            ship.translate(-10, 0);
        }
    }

    @Override
    public void hit() {
        Field.play("resources/explosion.wav");
        kill();
    }

    public void shoot(Shootable[] gameobjects) {
        Bullet bullet = new Bullet(this);
        bullet.shootUpwards(gameobjects);
    }


    public int getSpeed() {
        return 1;
    }


    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void kill() {
        ship.delete();
        active = false;
        Explosion.explode(this);

    }

    @Override
    public String toString() {
        return "SpaceShip";
    }
}