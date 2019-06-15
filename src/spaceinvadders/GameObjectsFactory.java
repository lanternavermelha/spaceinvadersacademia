package spaceinvadders;

public abstract class GameObjectsFactory {


    public static Shootable[] createCharacters(GameLevel level) {
        Shootable[] shootables = null;
        switch (level) {
            case ROOKIE:
                shootables = rookie();
                break;
            case INTERMEDIATE:
                shootables = intermediate();
                break;
            case PRO:
                shootables = pro();
                break;
            case INSANE:
                shootables = insane();
                break;
        }
        return shootables;
    }


    /**
     * Method to create Rookie Level GameObjects:
     * <p>
     * 15 Aliens / 5 Protection Blocs / 1 Boss / 1 Spaceship
     *
     * @return spaceinvadders.Shootable[]
     */

    private static Shootable[] rookie() {
        //TODO -- improve this magic numbers!!!!
        int arraySize = 22;
        int numOfAliens = 15;
        int numOfBricks = 5;
        int bossArrayPos = 20;
        int blocksGap = 50;
        int spaceshipArrayPos = arraySize - 1;

        Shootable[] shootables = new Shootable[arraySize];


        System.out.println("factory:" + 54);
        shootables[spaceshipArrayPos] = new SpaceShip(GameLevel.ROOKIE);

        //ALIENS CREATION
        int alienStartX = Field.getWIDTH() / 3;
        int alienStartY = 20;//this needs to be better

        for (int i = 0; i < shootables.length; i++) {
            if (i < numOfAliens) {
                shootables[i] = new Alien(alienStartX, alienStartY, GameLevel.ROOKIE);
                alienStartX += 70;//WHY 70 ? -- can we get it from a variable/constant?
                if (i == 4 || i == 9) {
                    alienStartX = Field.getWIDTH() / 3;
                    alienStartY = alienStartY + 50; //WHY 50?
                }
            }
            //Obstacle Creation
            if (i >= numOfAliens && i < numOfBricks + numOfAliens) {
                shootables[i] = new ProtectionBlock(blocksGap);
                blocksGap += 150;
            }
            //spaceinvadders.Boss Creation
            if (i == bossArrayPos) {
                shootables[i] = new Boss(Field.getWIDTH() / 2, 100, GameLevel.ROOKIE);
            }
        }
        return shootables;
    }

    /**
     * Method to create Intermediate Level GameObjects:
     * <p>
     * 20 Aliens / 4 Protection Blocs / 1 Boss / 1 Spaceship
     *
     * @return spaceinvadders.Shootable[]
     */

    private static Shootable[] intermediate() {
        //TODO -- improve enemys shoot or speed each level up?
        int arraySize = 26;
        int numOfAliens = 20;
        int numOfBricks = 4;
        int bossArrayPos = 24;
        int blocksGap = 120;
        int spaceshipArrayPos = arraySize - 1;

        Shootable[] shootables = new Shootable[arraySize];

        //GameObjects Creation

        //Player
        shootables[spaceshipArrayPos] = new SpaceShip(GameLevel.INTERMEDIATE);

        //ALIENS CREATION
        int alienStartX = Field.getWIDTH() / 3;
        int alienStartY = 20;//this needs to be better
        for (int i = 0; i < shootables.length; i++) {
            if (i < numOfAliens) {
                shootables[i] = new Alien(alienStartX, alienStartY, GameLevel.INTERMEDIATE);
                alienStartX += 70;//70 is the gap between aliens
                if (i == 4 || i == 9 || i == 14) {
                    alienStartX = Field.getWIDTH() / 3;
                    alienStartY = alienStartY + 50; //WHY 50?
                }
            }

            //Obstacle Creation
            if (i >= numOfAliens && i < numOfBricks + numOfAliens) {
                shootables[i] = new ProtectionBlock(blocksGap);
                blocksGap += 150;
            }
            //spaceinvadders.Boss Creation
            if (i == bossArrayPos) {
                shootables[i] = new Boss(Field.getWIDTH() / 2, 100, GameLevel.INTERMEDIATE);
            }
        }
        return shootables;
    }

    /**
     * Method to create Pro Level GameObjects:
     * <p>
     * 20 Aliens / 2 Protection Blocs / 2 Bosses / 1 Spaceship
     *
     * @return spaceinvadders.Shootable[]
     */

    private static Shootable[] pro() {
        //TODO -- improve enemys shoot or speed each level up?
        int arraySize = 25;
        int numOfAliens = 20;
        int numOfBricks = 2;
        int bossArrayPos = 22;
        int blocksGap = 220;//to be improved
        int spaceshipArrayPos = arraySize - 1;

        Shootable[] shootables = new Shootable[arraySize]; //spaceinvadders.Shootable array

        //GameObjects Creation
        //Player
        shootables[spaceshipArrayPos] = new SpaceShip(GameLevel.PRO);

        //ALIENS CREATION
        int alienStartX = Field.getWIDTH() / 3;
        int AlienStartY = 20;//this needs to be better
        for (int i = 0; i < shootables.length; i++) {
            if (i < numOfAliens) {
                shootables[i] = new Alien(alienStartX, AlienStartY, GameLevel.PRO);
                alienStartX += 70;
                if (i == 4 || i == 9 || i == 14) {
                    alienStartX = Field.getWIDTH() / 3;
                    AlienStartY = AlienStartY + 50; //WHY 50?
                }
            }

            //Obstacle Creation
            if (i == 20 || i == 21) {//TRY instead:  if (i >= numOfAliens && i < numOfBricks + numOfAliens) {
                shootables[i] = new ProtectionBlock(blocksGap);
                blocksGap += 250;//to be improved
            }
            //spaceinvadders.Boss Creation
            if (i == bossArrayPos) {
                shootables[i] = new Boss(1, 100, GameLevel.PRO);
            }
            if (i == bossArrayPos + 1) {
                shootables[i] = new Boss(0, 100, GameLevel.PRO);
            }
        }
        return shootables;
    }

    /**
     * Method to create Intermediate Level GameObjects:
     * <p>
     * 25 Aliens / no Protection Blocs / 3 Bosses / 1 Spaceship
     *
     * @return spaceinvadders.Shootable[]
     */
    private static Shootable[] insane() {
        //TODO -- improve this magic numbers!!!!
        int arraySize = 29;
        int numOfAliens = 25;
        int bossArrayPos = 25;
        int spaceshipArrayPos = arraySize - 1;

        Shootable[] shootables = new Shootable[arraySize];

        shootables[spaceshipArrayPos] = new SpaceShip(GameLevel.PRO);

        //ALIENS CREATION
        int alienStartX = Field.getWIDTH() / 3;
        int AlienStartY = 20;//this needs to be better

        for (int i = 0; i < shootables.length; i++) {
            if (i <= numOfAliens) {
                shootables[i] = new Alien(alienStartX, AlienStartY, GameLevel.PRO);
                alienStartX += 70;//WHY 70 ? -- can we get it from a variable/constant?
                if (i == 4 || i == 9 || i == 14 || i == 19) {
                    alienStartX = Field.getWIDTH() / 3;
                    AlienStartY = AlienStartY + 50; //WHY 50?
                }
            }

            if (i >= bossArrayPos && i <= spaceshipArrayPos - 1) {
                shootables[i] = new Boss(1, 100, GameLevel.PRO);
            }
        }
        return shootables;
    }
}

