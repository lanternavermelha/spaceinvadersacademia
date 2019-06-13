package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Explosion {



    public static void explode(Character character) {
        if (!character.isDead()) {
            final Picture explosion = new Picture(character.getX(), character.getY(), "/Users/albertoreis/dev/spaceinvadersgroup/resources/animation/bossexplosion1.png");
            if (character instanceof Alien) {
                Thread t1 = new Thread(new Runnable() {
                    public void animate(int i,Picture explosion) {

                        switch (i) {
                            case 0:
                                explosion.load("/Users/albertoreis/dev/spaceinvadersgroup/resources/animation/bossexplosion1.png");
                                break;
                            case 1:
                                explosion.load("/Users/albertoreis/dev/spaceinvadersgroup/resources/animation/bossexplosion2.png");
                                break;
                            case 2:
                                explosion.load("/Users/albertoreis/dev/spaceinvadersgroup/resources/animation/bossexplosion3.png");
                                break;
                            case 3:
                                explosion.load("/Users/albertoreis/dev/spaceinvadersgroup/resources/animation/bossexplosion4.png");
                                break;
                            case 4:
                                explosion.load("/Users/albertoreis/dev/spaceinvadersgroup/resources/animation/bossexplosion5.png");
                                break;
                            case 5:
                                explosion.load("/Users/albertoreis/dev/spaceinvadersgroup/resources/animation/bossexplosion6.png");
                                break;
                            case 6:
                                explosion.load("/Users/albertoreis/dev/spaceinvadersgroup/resources/animation/bossexplosion7.png");
                                break;
                            default:
                                explosion.delete();

                        }
                    }
                    public void run() {
                        explosion.draw();
                        for (int i = 0; i < 8; i++) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                            animate(i,explosion);
                        }
                    }
            });
            t1.start();
        }


    }

}


}


