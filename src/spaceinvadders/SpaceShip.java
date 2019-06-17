package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SpaceShip implements Shootable {

    private boolean active;
    private Picture ship;
    private GameLevel gameLevel;


    private Field field;

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


    SpaceShip(GameLevel gameLevel) {
        ship = new Picture(Field.getWIDTH() / 2, Field.getHEIGHT() - 100, "resources/ship.png");
        ship.draw();
        active = true;
        this.gameLevel = gameLevel;
    }

    void moveRight() {
        if (getX() + getWidth() < Field.getWIDTH()) {
            if (gameLevel == GameLevel.ROOKIE || gameLevel == GameLevel.INTERMEDIATE) {
                ship.translate(10, 0);
            } else {
                ship.translate(20, 0);
            }
        }
    }

    void moveLeft() {

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
        active = false;
    }

    void shoot(Shootable[] gameobjects) {
        if (isActive()) {
            Bullet bullet = new Bullet(this);
            bullet.shootUpwards(gameobjects);
        }

    }

    void gimmeField(Field field) {
        this.field = field;

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
        field.setGameOver();

    }

    @Override
    public String toString() {
        return "SpaceShip";
    }
}