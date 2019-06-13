import spaceinvadders.*;
import spaceinvadders.Character;

public class ClassTests {
    public static void main(String[] args) {


        //Factory TEST!
        Character[] a = GameObjectsFactory.createCharacters(GameLevel.INSANE);
        int aa = 0;
        int pb = 0;
        int bb = 0;
        int ss = 0;

        for (Character character : a) {
            if (character instanceof Alien) {
                aa++;
            }
            if (character instanceof ProtectionBlock) {
                pb++;
            }
            if (character instanceof Boss) {
                bb++;
            }
            if (character instanceof SpaceShip) {
                ss++;
            }
        }System.out.println("Array size: " + a.length + "---> Alien: " + aa + " -- ProtectionBlock: " + pb + " -- Boss: " + bb + " -- SpaceShip: " + ss);
    }
}


/* MULTITHREADING BULLETMOVE TEST


        Rectangle background = new Rectangle(10,10,800,600);
        background.setColor(Color.BLUE);
        background.fill();

        Rectangle bala = new Rectangle(400,500,3,8);
        bala.setColor(Color.YELLOW);
        bala.fill();

        Rectangle outrabala = new Rectangle(450,0,3,8);
        outrabala.setColor(Color.GREEN);
        outrabala.fill();


        Thread t1 = new Thread(new Runnable() {
            public void run()
            {while (background.getHeight()>0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                bala.translate(0,-10);
                System.out.println(bala.getY());
            }
            }});
        t1.start();



        Thread t2 = new Thread(new Runnable() {
            public void run()
            {while (bala.getHeight()<background.getHeight()){
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                outrabala.translate(0,10);
                System.out.println(outrabala.getY());
            }
            }});
        t2.start();
    }
*/

