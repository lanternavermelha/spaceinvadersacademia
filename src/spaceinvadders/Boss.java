package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Boss extends BadGuys {

    private Picture representation;
    private int hitPoints;
    private boolean active;
    private GameLevel gameLevel;
    private volatile boolean exit = false;
    int speed = 0;

    private void levelSpeed(GameLevel gameLevel) {

        switch (gameLevel) {
            case ROOKIE:
                speed = 11;
                break;
            case INTERMEDIATE:
                speed = 9;
                break;
            case PRO:
                speed = 6;
                break;
            case INSANE:
                speed = 5;
                break;
        }


    }

    private void shuffleRepresentation() {
        double ran = Math.random();
        if (ran < .4) {
            representation = new Picture(200 * ran + 50, 100 * ran + 200, "resources/super.png");
        }
        if (ran >= .4 && ran < .7) {
            representation = new Picture(400 * ran + 200, 100 * ran + 150, "resources/super2.png");
        }
        if (ran >= .7) {
            representation = new Picture(600 * ran + 100, 100 * ran + 100, "resources/super3.png");
        }
    }

    public Boss(GameLevel gameLevel) {//not doing shit with this values!!

        this.gameLevel = gameLevel;
        shuffleRepresentation();
        hitPoints = 300;
        levelSpeed(gameLevel);

    }

    public void hit() {
        hitPoints -= 50;
        Explosion.explode(this);
        if (hitPoints == 0) {
            kill();
            Explosion.explode(this);
            Explosion.explode(this);
            Explosion.explode(this);
        }

    }

    public void kill() {
        exit = true;
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
                    while (!exit) {
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
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            representation.translate(-1, 0);
        }
        //To the left
        while (getX() < Field.getWIDTH() - Field.getPADDING() - getWidth()) {
            try {
                Thread.sleep(speed);
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
        int probability = (int) Math.floor(Math.random() * 200);
        if (probability == 1) {
            System.out.println(probability);
            if (isActive()) {
                System.out.println("HE IS ACTIVE");
                Bullet bullet = new Bullet(this);
                bullet.shootDownwards(shootables);
                System.out.println("HE IS ACTIVE");
            }
        }

    }

    @Override
    public String toString() {
        return "Boss";
    }
}