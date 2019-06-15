package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Bullet {

    private Picture representation;
    private Shootable[] targets;

    public Bullet(Shootable shootable) {
        bulletType(shootable);

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
            //changed Y to shoot from the bottom of the character, need to find a better way
            representation = new Picture(centerX, centerY + shootable.getHeight(), "resources/bossprojectile.png");
        }
        if (shootable instanceof Alien) {
            representation = new Picture(centerX, centerY + shootable.getHeight(), "resources/enemyprojectile.png");
        }
        representation.draw();
    }

    public void shootUpwards(final Shootable[] shootables) {
        targets = shootables;
        Thread bulletTrajectory = new Thread(new Runnable() {
            public void run() {
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
                            return;
                        }
                    }
                }
                representation.delete();
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
        return representation.getY() == target.getY() + target.getHeight()
                && representation.getX() + representation.getWidth() <= target.getX() + target.getWidth()
                && representation.getX() >= target.getX();
    }


    public void shootDownwards() {

        representation.draw();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (representation.getY() + representation.getHeight() < Field.getHEIGHT() - Field.getPADDING()) {
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    representation.translate(0, 1);
                }
                representation.delete();
            }
        });

        t1.start();
    }
}
