package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullet {

    Picture representation;

    public Bullet(Character character) {
        if (character instanceof SpaceShip) {
            representation = new Picture(character.getX() + (character.getWidth() / 2), character.getY() - character.getHeight() / 2, "/Users/albertoreis/dev/spaceinvadersgroup/resources/projectile.png");
        }
        if (character instanceof Boss) {
            representation = new Picture(character.getX() + (character.getWidth() / 2), character.getY() + character.getHeight(), "/Users/albertoreis/dev/spaceinvadersgroup/resources/bossprojectile.png");
        }
        if (character instanceof Alien){
            representation = new Picture(character.getX() + (character.getWidth() / 2), character.getY() + character.getHeight(), "/Users/albertoreis/dev/spaceinvadersgroup/resources/enemyprojectile.png");
        }
    }

    public void shootUpwards(Character[] characters) {
        representation.draw();
        Character[] character = characters;

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (representation.getY() > Field.getPADDING()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    representation.translate(0, -3);
                }
                representation.delete();
            }
        });
        t1.start();
        for (int i = characters.length - 1; i >= 0; i--) {
            if (hitChecker(characters[i])) ;
            break;
        }
    }

    public boolean hitChecker(Character target) {
        if (!target.isDead()) {
            if (target instanceof Alien) {
                if (representation.getY() == target.getY() + target.getHeight()
                        && representation.getX() <= target.getX() + target.getWidth()
                        && representation.getX() >= target.getX()) {
                    representation.delete();
                    target.kill();
                    Explosion.explode(target);
                    return true;
                }
            }
        }return false;
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
