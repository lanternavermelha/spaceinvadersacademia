package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SpaceShip implements Shootable {

    private boolean active;
    private Picture ship;
    private GameLevel gameLevel;




    public SpaceShip(GameLevel gameLevel) {
        ship = new Picture(Field.getWIDTH() / 2, Field.getHEIGHT() - 100, "resources/ship.png");
        ship.draw();
        active = true;
        this.gameLevel = gameLevel;
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


    public void moveRight() {
        if (getX() + getWidth() < Field.getWIDTH()) {
            if (gameLevel == GameLevel.ROOKIE || gameLevel == GameLevel.INTERMEDIATE) {
                ship.translate(10, 0);
            } else {
                ship.translate(20, 0);
            }
        }
    }


    public void moveLeft() {

        if (getX() > Field.getPADDING()) {
            if (gameLevel == GameLevel.ROOKIE || gameLevel == GameLevel.INTERMEDIATE) {
                ship.translate(-10, 0);
            } else {
                ship.translate(-20, 0);
            }
        }
    }

    @Override
    public void hit() {
        kill();
        Explosion.explode(this);
    }

    public void shoot(Shootable[] gameobjects) {

        Bullet bullet = new Bullet(this);
        bullet.shootUpwards(gameobjects);
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