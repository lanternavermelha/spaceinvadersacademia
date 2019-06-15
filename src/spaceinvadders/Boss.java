package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Boss extends BadGuys {

    private Picture representation;
    private int hitPoints;
    private boolean active;

    public Boss(int x, int y, GameLevel gameLevel) {//not doing shit with this values!!
        //TODO switch to change speed and shooting pattern

        hitPoints = 300;
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
        if (hitPoints == 0) {
            kill();
        }
        Field.play("resources/bulletsound.wav");
    }

    public void kill() {
        active = false;
        representation.delete();
        Explosion.explode(this);
    }

    /**
     * activate the animation
     */
    public void activate() {
        representation.draw();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    move();
                }
            }
        });
        t1.start();
        active = true;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public int getSpeed() {
        return 0;
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

    @Override
    public void shoot(Shootable[] shootables) {
        Bullet bullet = new Bullet(this);
        bullet.shootDownwards();
    }

    public void move() {
//TODO maybe here to implement different speed
        while (getX() > Field.getPADDING()) {
            representation.translate(-5, 0);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        while (getX() < Field.getWIDTH() - Field.getPADDING() - getWidth()) {
            representation.translate(+5, 0);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String toString() {
        return "Boss";
    }
}