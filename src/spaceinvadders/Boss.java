package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Boss extends BadGuys {

    private Picture representation;
    private int hitPoints;
    private boolean active;
    private GameLevel gameLevel;

    public Boss(int x, int y, GameLevel gameLevel) {//not doing shit with this values!!

        this.gameLevel = gameLevel;

        hitPoints = 300;

        //TODO better way to randomize position?
        double ran = Math.random();
        if (ran < .4) {
            representation = new Picture(200, 100, "resources/super.png");
        }
        if (ran >= .4 && ran < .7) {
            representation = new Picture(400, 100, "resources/super2.png");
        }
        if (ran >= .7) {
            representation = new Picture(600, 100, "resources/super3.png");
        }
        //activate();
    }

    public void hit() {
        hitPoints -= 50;
        AudioPlay bossHit = new AudioPlay("resources/bulletsound.wav");
        bossHit.runAudio();

        if (hitPoints == 0) {
            kill();
            Explosion.explode(this);
            //return;
        }
        bossHit.stopAudio();

        //Field.playSound("resources/bulletsound.wav");
    }

    public void kill() {
        active = false;
        representation.delete();
        Explosion.explode(this);
    }

    /**
     * Initiate a new Thread to move Bosses
     */
    public void activate() {
        if (!active) {
            representation.draw();
            Thread bossMove = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        move();
                    }
                }
            });
            bossMove.start();
            active = true;
        }
    }

    /**
     * loops the boss movement right - left - right
     */
    public void move() {
        //TODO maybe here to implement different speed
        //To the right
        while (getX() > Field.getPADDING()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            representation.translate(-1, 0);
        }
        //To the left
        while (getX() < Field.getWIDTH() - Field.getPADDING() - getWidth()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            representation.translate(+1, 0);
        }
    }


    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public GameLevel getGameLevel() {
        return gameLevel;
    }

    @Override
    public int getX() {
        return representation.getX();
    }

    @Override
    public int getY() {
        return representation.getY();
    }

    @Override
    public int getWidth() {
        return representation.getWidth();
    }

    @Override
    public int getHeight() {
        return representation.getHeight();
    }
//TODO needs to implement this method

    @Override
    public void shoot(Shootable[] shootables) {
        Bullet bullet = new Bullet(this);
        bullet.shootDownwards();
    }

    @Override
    public String toString() {
        return "Boss";
    }
}