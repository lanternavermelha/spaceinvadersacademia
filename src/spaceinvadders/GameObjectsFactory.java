package spaceinvadders;

import java.util.Arrays;

public abstract class GameObjectsFactory {


    public static Character[] createCharacters(GameLevel level) {
        Character[] characters = null;
        switch (level) {
            case ROOKIE:
                characters = rookie();
                break;
            case INTERMEDIATE:
                characters = intermediate();
                break;
            case PRO:
                characters = pro();
                break;
            case INSANE:
                characters = insane();
                break;
        }
        return characters;
    }


    /**
     * Method to create Rookie Level GameObjects:
     *
     * 15 Aliens / 5 Protection Blocs / 1 spaceinvadders.Boss / 1 Spaceship
     *
     * @return spaceinvadders.Character[]
     */

    private static Character[] rookie() {
        //TODO -- improve this magic numbers!!!!
        int arraySize = 22;
        int numOfAliens = 15;
        int numOfBricks = 5;
        int bossArrayPos = 20;
        int blocksGap = 50;//to be improved
        int alienSpeed = 5;
        int spaceshipArrayPos =arraySize-1;

        Character[]characters = new Character[arraySize]; //spaceinvadders.Character array

        //GameObjects Creation
        //Player
        characters[spaceshipArrayPos] = new SpaceShip(0, 0, 5);

        //ALIENS CREATION
        int alienStartX = Field.getWIDTH() / 3;
        int AlienStartY = 20;//this needs to be better

        for (int i = 0; i < characters.length; i++) {
            if (i < numOfAliens) {
                characters[i] = new Alien(alienStartX, AlienStartY, alienSpeed);
                alienStartX += 70;//WHY 70 ? -- can we get it from a variable/constant?
                if (i == 4 || i == 9) {
                    alienStartX = Field.getWIDTH() / 3;
                    AlienStartY = AlienStartY + 50; //WHY 50?
                }
            }

            //Obstacle Creation
            if (i > numOfAliens - 1 && i < numOfBricks + numOfAliens) {
                characters[i] = new ProtectionBlock(blocksGap, 0, 0);
                blocksGap += 150;//to be improved
            }
            //spaceinvadders.Boss Creation
            if (i == bossArrayPos) {
                characters[i] = new Boss(Field.getWIDTH() / 2, 100, 20);
            }
        }

        System.out.println(Arrays.toString(characters));
        return characters;
    }

    /**
     * Method to create Intermediate Level GameObjects:
     *
     * 20 Aliens / 4 Protection Blocs / 1 spaceinvadders.Boss / 1 Spaceship
     *
     * @return spaceinvadders.Character[]
     */

    private static Character[] intermediate() {
        //TODO -- improve enemys shoot or speed each level up?
        int arraySize = 26;
        int numOfAliens = 20;
        int numOfBricks = 4;
        int bossArrayPos = 24;
        int blocksGap = 120;//to be improved
        int alienSpeed = 10;
        int spaceshipArrayPos =arraySize-1;

        Character[]characters = new Character[arraySize]; //spaceinvadders.Character array

        //GameObjects Creation
        //Player
        characters[spaceshipArrayPos] = new SpaceShip(0, 0, 5);

        //ALIENS CREATION
        int alienStartX = Field.getWIDTH() / 3;
        int AlienStartY = 20;//this needs to be better
        for (int i = 0; i < characters.length; i++) {
            if (i < numOfAliens) {
                characters[i] = new Alien(alienStartX, AlienStartY, alienSpeed);
                alienStartX += 70;//WHY 70 ? -- can we get it from a variable/constant?
                if (i == 4 || i == 9||i==14) {
                    alienStartX = Field.getWIDTH() / 3;
                    AlienStartY = AlienStartY + 50; //WHY 50?
                }
            }

            //Obstacle Creation
            if (i > numOfAliens - 1 && i < numOfBricks + numOfAliens) {
                characters[i] = new ProtectionBlock(blocksGap, 0, 0);
                blocksGap += 150;//to be improved
            }
            //spaceinvadders.Boss Creation
            if (i == bossArrayPos) {
                characters[i] = new Boss(Field.getWIDTH() / 2, 100, 20);
            }
        }

        System.out.println(Arrays.toString(characters));
        return characters;
    }
    /**
     * Method to create Pro Level GameObjects:
     *
     * 20 Aliens / 2 Protection Blocs / 2 Bosses / 1 Spaceship
     *
     * @return spaceinvadders.Character[]
     */

    private static Character[] pro() {
        //TODO -- improve enemys shoot or speed each level up?
        int arraySize = 25;
        int numOfAliens = 20;
        int numOfProtection = 2;
        int bossArrayPos = 22;
        int blocksGap = 220;//to be improved
        int alienSpeed = 15;
        int spaceshipArrayPos =arraySize-1;

        Character[]characters = new Character[arraySize]; //spaceinvadders.Character array

        //GameObjects Creation
        //Player
        characters[spaceshipArrayPos] = new SpaceShip(0, 0, 5);

        //ALIENS CREATION
        int alienStartX = Field.getWIDTH() / 3;
        int AlienStartY = 20;//this needs to be better
        for (int i = 0; i < characters.length; i++) {
            if (i < numOfAliens) {
                characters[i] = new Alien(alienStartX, AlienStartY, alienSpeed);
                alienStartX += 70;//WHY 70 ? -- can we get it from a variable/constant?
                if (i == 4 || i == 9||i==14) {
                    alienStartX = Field.getWIDTH() / 3;
                    AlienStartY = AlienStartY + 50; //WHY 50?
                }
            }

            //Obstacle Creation
            if (i ==20 ||i==21 ) {
                characters[i] = new ProtectionBlock(blocksGap, 0, 0);
                blocksGap += 250;//to be improved
            }
            //spaceinvadders.Boss Creation
            if (i == bossArrayPos ) {
                characters[i] = new Boss(1, 100, 20);
            }
            if (i== bossArrayPos+1) {
                characters[i] = new Boss(0, 100, 20);
            }
        }

        System.out.println(Arrays.toString(characters));
        return characters;
    }
    /**
     * Method to create Intermediate Level GameObjects:
     *
     * 25 Aliens / no Protection Blocs / 3 Bosses / 1 Spaceship
     *
     * @return spaceinvadders.Character[]
     */
    private static Character[] insane() {
        //TODO -- improve this magic numbers!!!!
        int arraySize = 29;
        int numOfAliens = 25;
        int bossArrayPos = 25;
        int alienSpeed = 35;
        int spaceshipArrayPos =arraySize-1;

        Character[]characters = new Character[arraySize]; //spaceinvadders.Character array

        //GameObjects Creation
        //Player
        characters[spaceshipArrayPos] = new SpaceShip(0, 0, 5);

        //ALIENS CREATION
        int alienStartX = Field.getWIDTH() / 3;
        int AlienStartY = 20;//this needs to be better

        for (int i = 0; i < characters.length; i++) {
            if (i < numOfAliens) {
                characters[i] = new Alien(alienStartX, AlienStartY, alienSpeed);
                alienStartX += 70;//WHY 70 ? -- can we get it from a variable/constant?
                if (i == 4 || i == 9||i==14||i==19) {
                    alienStartX = Field.getWIDTH() / 3;
                    AlienStartY = AlienStartY + 50; //WHY 50?
                }
            }
            //spaceinvadders.Boss Creation
            if (i == bossArrayPos || i==bossArrayPos+2) {
                characters[i] = new Boss(1, 100, 20);
            }
            if (i== bossArrayPos+1) {
                characters[i] = new Boss(0, 100, 20);
            }
        }

        System.out.println(Arrays.toString(characters));
        return characters;
    }
}
