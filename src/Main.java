import spaceinvadders.Field;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(200);
        field.init();

    }
}


//spaceinvadders.Character[] k = spaceinvadders.GameObjectsFactory.createCharacters(spaceinvadders.GameLevel.INTERMEDIATE);

//System.out.println(Arrays.toString(k));

/*

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
}

/*
public void newmove(spaceinvadders.Alien[] aliens) {
        for (spaceinvadders.Alien a :aliens) {
            while (updateAlienReferenceRight(aliens).representation.getX() + aliens[0].representation.getWidth() < WIDTH - PADDING) {
                a.moveRight();
            }
            while (updateAlienReferenceLeft(aliens).representation.getX() > PADDING + aliens[0].representation.getWidth()) {
                a.moveLeft();

            }





    }*/


