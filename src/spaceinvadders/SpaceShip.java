package spaceinvadders;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SpaceShip extends Character {
//spaceinvadders.SpaceShip -- changed atributes to private, getx and GetY, getwidht getheight

    private boolean dead;
    private Picture ship;
    private Rectangle bullet;
    private int speed;
    private int playerLevel;

    @Override
    public int getSpeed() {
        return speed;
    }

    private int aliensKilled;

    public SpaceShip(int x, int y, int speed) {
        ship = new Picture(Field.getWIDTH() / 2, Field.getHEIGHT() - 100, "Resources/ship.png");
        ship.draw();
        this.speed = 10;//speed;
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

    @Override
    public void shoot() {
        Bullet bullet = new Bullet(this);
        bullet.shootUpwards();
    }


    public void moveDown() {
        ship.translate(0, 20);
    }

    public void moveUp() {
        // representation.translate(0, -20);
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

    public boolean hitCheckerNOARRAY(Character character) {
        boolean result = false;
        if (!character.isDead()) {
            if (bullet.getY() == character.getY() + character.getHeight()
                    //To get the bottom of the figure
                    || bullet.getX() <= character.getX() + character.getWidth()
                    && bullet.getX() >= character.getX()) {
                character.kill();
                result = true;
                if (character instanceof Alien) {
                    aliensKilled++;
                }
                // System.out.println("I killed: " + aliensKilled);
                playerLevel++;
            }
        }
        return result;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void kill() {
        ship.delete();
        dead = true;
    }

    public void hitChecker(Character[] aliens) {
        for (int i = aliens.length - 1; i >= 0; i--) {
            if (!aliens[i].isDead()) {
                if (bullet.getY() == aliens[i].getY() + aliens[i].getHeight()//To get the bottom of the figure
                        || bullet.getX() <= aliens[i].getX() + aliens[i].getWidth()
                        && bullet.getX() >= aliens[i].getX()) {
                    aliens[i].kill();
                    //  System.out.println("alien " + i + " is dead");
                    aliensKilled++;
                    // System.out.println("I killed: " + aliensKilled);
                    if (aliensKilled == aliens.length) {
                        playerLevel++;
                        //   System.out.println("Level up! " + playerLevel);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "SpaceShip";
    }
}