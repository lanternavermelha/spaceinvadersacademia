package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Alien extends BadGuys {

    private boolean active;
    private GameLevel gameLevel;
    private Picture representation;
    private int bulletRate;

    Alien(int x, int y, GameLevel gameLevel) {
        //TODO switch to change speed and shooting pattern
        this.gameLevel = gameLevel;
        shuffleRepresentation(x, y);
        active = true;

        if (gameLevel == GameLevel.ROOKIE || gameLevel == GameLevel.INTERMEDIATE) {
            bulletRate = 2;
        }
        if (gameLevel == GameLevel.INSANE || gameLevel == GameLevel.PRO) {
            bulletRate = 3;
        }


    }

    /**
     * Method to shuffle the representation of the alien enemies
     *
     * @param x
     * @param y
     */

    private void shuffleRepresentation(int x, int y) {
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
    }


    public GameLevel getGameLevel() {
        return gameLevel;
    }

    @Override
    public void shoot(Shootable[] characters) {
        int probability = (int) Math.floor(Math.random() * 2000);
        if (probability > 0 && probability < bulletRate) {
            if (isActive()) {
                Bullet bullet = new Bullet(this);
                bullet.shootDownwards(characters);
            }
        }
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

    void moveRight() {
        representation.translate(1, 0);
    }


    void moveLeft() {
        representation.translate(-1, 0);
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

    void moveDown() {
        representation.translate(0, getHeight());
    }

    @Override
    public String toString() {
        return "Alien";
    }
}

