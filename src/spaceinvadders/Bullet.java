package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;


class Bullet {

    private Picture representation;
    private Shootable[] targets;
    private volatile boolean exit = false;
    private volatile boolean exit2 = false;
    private boolean active;

    private Shootable shooter;

    Bullet(Shootable shootable) {
        shooter = shootable;
        bulletType(shootable);
    }

    boolean isActive() {
        return active;
    }

    private void bulletType(Shootable shootable) {
        int shipBulletPosX = shootable.getX() + (shootable.getWidth() / 2);
        int shipBulletPosY = shootable.getY();
        int centerX = shootable.getX() + (shootable.getWidth() / 2);
        int centerY = shootable.getY() - (shootable.getHeight() / 2);
        if (shootable instanceof SpaceShip) {
            representation = new Picture(shipBulletPosX, shipBulletPosY, "resources/projectile.png");
        }
        if (shootable instanceof Boss) {
            representation = new Picture(centerX, centerY + shootable.getHeight(), "resources/bossprojectile.png");
        }
        if (shootable instanceof Alien) {
            representation = new Picture(centerX, centerY + shootable.getHeight(), "resources/enemyprojectile.png");
        }
        representation.draw();
        active = true;
    }

    void shootUpwards(final Shootable[] shootables) {
        targets = shootables;
        Thread bulletTrajectory = new Thread(new Runnable() {
            public void run() {
                while (!exit) {
                    if (shooter.isActive()) {
                        exit = true;
                    }
                    while (representation.getY() > Field.getPADDING()) {
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        representation.translate(0, -1);
                        for (Shootable target : shootables
                        ) {
                            if (target.isActive() && isSamePosUp(target)) {
                                representation.delete();
                                target.hit();
                                exit = true;
                                return;
                            }
                        }
                    }
                    exit = true;
                    representation.delete();
                }
            }
        });
        bulletTrajectory.start();


    }

    private boolean isSamePosUp(Shootable target) {
        return representation.getY() <= target.getY() + target.getHeight() && representation.getY() >= target.getY()
                && representation.getX() <= target.getX() + target.getWidth()
                && representation.getX() >= target.getX();
    }

    private boolean isSamePosDown(Shootable target) {
        return representation.getY() == target.getY()
                && representation.getX() + representation.getWidth() <= target.getX() + target.getWidth()
                && representation.getX() >= target.getX();
    }


    void shootDownwards(final Shootable[] shootables) {
        exit2=false;
        targets = shootables;
        representation.draw();
        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (!exit2) {
                    while (representation.getY() + representation.getHeight() < Field.getHEIGHT() - Field.getPADDING()) {
                        try {
                            Thread.sleep(4);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        for (Shootable target : shootables) {
                            if (!(target instanceof BadGuys)) {
                                if (target.isActive() && isSamePosUp(target)) {
                                    representation.delete();
                                    target.hit();
                                    exit2=true;
                                    return;
                                }
                            }

                        }
                        if (isActive()) {
                            representation.translate(0, 1);
                        }
                    }
                    exit2=true;
                    representation.delete();
                }
            }
        });

        t1.start();
    }

}
