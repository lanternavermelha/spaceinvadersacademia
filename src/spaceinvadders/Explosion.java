package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Explosion {

    private static void animate(int i, Picture explosion) {
        switch (i) {
            case 0:
                explosion.load("resources/animation/bossexplosion2.png");
                break;
            case 1:
                explosion.load("resources/animation/bossexplosion3.png");
                break;
            case 2:
                explosion.load("resources/animation/bossexplosion4.png");
                break;
            case 3:
                explosion.load("resources/animation/bossexplosion5.png");
                break;
            case 4:
                explosion.load("resources/animation/bossexplosion6.png");
                break;
            case 5:
                explosion.load("resources/animation/bossexplosion7.png");
                break;
            default:
                explosion.delete();
        }
    }

    public static void explode(Shootable shootable) {
        Picture explosion = new Picture(shootable.getX()+shootable.getWidth()/2, shootable.getY()-shootable.getHeight()/2, "resources/animation/bossexplosion1.png");
        explosion.draw();
        for (int i = 0; i < 6; i++) {
            sleep(15);
            animate(i, explosion);
        }
  //      Field.playSound("resources/bulletsound.wav");
        explosion.delete();
    }

    private static void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}


