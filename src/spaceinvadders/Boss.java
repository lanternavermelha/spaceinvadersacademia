package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Boss extends BadGuys {

    private Picture representation;
    private int hitPoints;
    private boolean active;
    private GameLevel gameLevel;
    private volatile boolean exit = false;
    int speed = 0;
    private Shootable[] targets;

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

    private Field field;

    public void gimmeTargets(Shootable[] targets) {
        this.targets = targets;
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

    private void shuffleRepresentation() {
        double ran = Math.random();
        if (ran < .4) {
            representation = new Picture(200 * ran + 150, 100 * ran + 200, "resources/super.png");
        }
        if (ran >= .4 && ran < .7) {
            representation = new Picture(400 * ran + 200, 100 * ran + 150, "resources/super2.png");
        }
        if (ran >= .7) {
            representation = new Picture(600 * ran + 100, 100 * ran + 100, "resources/super3.png");
        }
    }

    public void kill() {
        field.setBosscount(1);
        exit = true;
        active = false;
        representation.delete();
        Explosion.explode(this);
    }

    /**
     * Initiate a new Thread to move Bosses
     */
    public void activate() {
        System.out.println("boss active!!!");
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

    /**
     * loops the boss movement right - left - right
     */
    public void move() {
        //TODO maybe here to implement different speed
        while (getX() > Field.getPADDING()) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (active) {
                shoot(targets);
                representation.translate(-1, 0);
            }
        }
        while (getX() < Field.getWIDTH() - Field.getPADDING() - getWidth()) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (active) {
                shoot(targets);
                representation.translate(+1, 0);
            }
        }
    }

    @Override
    public void shoot(Shootable[] shootables) {
        int probability = (int) Math.floor(Math.random() * 900);
        if (probability == 1) {
            if (isActive()) {
                Bullet bullet = new Bullet(this);
                bullet.shootDownwards(targets);
            }
        }

    }

    public void gimmeField(Field field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Boss";
    }
}