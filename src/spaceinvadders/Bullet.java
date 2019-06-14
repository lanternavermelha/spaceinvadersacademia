package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Bullet {

    private Picture representation;
    private Shootable[] targets;


    public Bullet(Shootable shootable) {
        int centerX = shootable.getX() + (shootable.getWidth() / 2);
        int centerY = shootable.getY() - (shootable.getHeight() / 2);

        if (shootable instanceof SpaceShip) {
            //changed Y to shoot from the bottom of the character, need to find a better way
            representation = new Picture(centerX, centerY+30, "/Users/albertoreis/dev/spaceinvadersgroup/resources/projectile.png");
        }
        if (shootable instanceof Boss) {
            //changed Y to shoot from the bottom of the character, need to find a better way
            representation = new Picture(centerX, centerY+ shootable.getHeight(), "/Users/albertoreis/dev/spaceinvadersgroup/resources/bossprojectile.png");
        }
        if (shootable instanceof Alien) {
            representation = new Picture(centerX, centerY, "/Users/albertoreis/dev/spaceinvadersgroup/resources/enemyprojectile.png");
        }
        representation.draw();
    }

    public void shootUpwards(final Shootable[] shootables) {
        targets = shootables;
        System.out.print(Arrays.toString(targets));
         Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (representation.getY() > Field.getPADDING()) {
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    representation.translate(0, -1);
                    for (Shootable target : shootables
                    ) { if (target.isVisible() && isSamePos(target)) {
                            representation.delete();
                            target.hit();
                        return;
                        }
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
