package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullet {

    private Picture representation;
    private Shootable[] targets;


    public Bullet(Shootable shootable) {
        int centerX = shootable.getX() + (shootable.getWidth() / 2);
        int centerY = shootable.getY() - (shootable.getHeight() / 2);

        if (shootable instanceof SpaceShip) {
            representation = new Picture(centerX, centerY, "/Users/albertoreis/dev/spaceinvadersgroup/resources/projectile.png");
        }
        if (shootable instanceof Boss) {
            representation = new Picture(centerX, centerY, "/Users/albertoreis/dev/spaceinvadersgroup/resources/bossprojectile.png");
        }
        if (shootable instanceof Alien) {
            representation = new Picture(centerX, centerY, "/Users/albertoreis/dev/spaceinvadersgroup/resources/enemyprojectile.png");
        }
        representation.draw();
    }

    public void shootUpwards(Shootable[] shootables) {
        targets = shootables;

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (representation.getY() > Field.getPADDING()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    representation.translate(0, -3);
                    //System.out.println(Arrays.toString(targets));
                    for (int i = targets.length - 1; i >= 0; i--) {
                        System.out.println("inicio for indice: " + i);
                        if (targets[i].isVisible() && isSamePos(targets[i])) {
                            representation.delete();
                            targets[i].hit();
                            System.out.println("aaaa");
                        }
                        //if (hitChecker(targets[i])) {
                        //  System.out.println("Aqui! " + targets[i]);
                        //}
                    }
                }
                representation.delete();
            }
        });
        t1.start();
    }

    private boolean isSamePos(Shootable target) {
        return representation.getY() == target.getY() + target.getHeight()
                && representation.getX() <= target.getX() + target.getWidth()
                && representation.getX() >= target.getX();
    }

    public boolean hitChecker(Shootable target) {
        if (target.isVisible()
                && representation.getY() == target.getY() + target.getHeight()
                && representation.getX() <= target.getX() + target.getWidth()
                && representation.getX() >= target.getX()) {
            representation.delete();
            target.hit();
            return true;
        }
        return false;
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
                    System.out.println(representation.getX());
                    System.out.println(representation.getY());
                }
                representation.delete();
            }
        });

        t1.start();
    }

    public int getX() {
        return representation.getX();
    }


    public int getY() {
        return representation.getY();
    }

}
