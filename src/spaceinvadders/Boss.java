package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Boss extends Character {
    private boolean dead;
    private Picture representation;
    private int health;
    private boolean visible;

    public Boss(int x, int y, int speed) {//not doing shit with this values!!
        health = 50;
        double ran = Math.random();
        if (ran < .4) {
            representation = new Picture(200, 100, "Resources/super.png");
        }
        if (ran >= .4 && ran < .7) {
            representation = new Picture(400, 100, "Resources/super2.png");
        }
        if (ran >= .7) {
            representation = new Picture(600, 100, "Resources/super3.png");
        }
        beVisible();
    }


    public void kill() {
        dead = true;
        representation.delete();
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    public boolean isDead() {
        return dead;
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


    public void beVisible() {
        representation.draw();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    move();
                }
            }
        });
        t1.start();
        visible = true;
    }

    @Override
    public void shoot(Character[] characters) {
        Bullet bullet = new Bullet(this);
        bullet.shootDownwards();
    }

    public void move() {
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