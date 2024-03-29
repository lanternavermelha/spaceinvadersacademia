package spaceinvadders;

abstract class AlienHorde {

    /**
     * checks if all aliens in the horde are dead.
     *
     * @param gameObjects
     * @return true if all aliens are dead
     */

    private static int deadCount;

    private static int levelSpeed(GameLevel gameLevel) {
        int speed = 0;

        switch (gameLevel) {
            case ROOKIE:
                speed = 20;
                break;
            case INTERMEDIATE:
                speed = 17;
                break;
            case PRO:
                speed = 14;
                break;
            case INSANE:
                speed = 10;
                break;
        }
        return speed;
    }

    static void move(Shootable[] gameObjects) {
        Alien[] horde = selectHorde(gameObjects);
        int speed = levelSpeed(horde[0].getGameLevel());
        int gameOverLimmit = 420;

        while (horde[horde.length - 1].getY() <= gameOverLimmit) {

            while (alienReferenceRight(horde).getX() + alienReferenceRight(horde).getWidth() <= Field.getWIDTH() - Field.getPADDING()) {

                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < horde.length; i++) {
                    if (bossIsReady(gameObjects)) {
                        return;
                    }
                    if (gameObjects[gameObjects.length - 1].isActive()) {
                        horde[i].moveRight();
                        horde[i].shoot(gameObjects);
                    } else return;
                }
            }

            for (Alien a : horde) {
                a.moveDown();
            }

            while (alienReferenceLeft(horde).getX() > Field.getPADDING() + horde[0].getWidth()) {
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < horde.length; i++) {
                    if (bossIsReady(gameObjects)) {
                        return;
                    }
                    if (gameObjects[gameObjects.length - 1].isActive()) {
                        horde[i].moveLeft();
                        horde[i].shoot(gameObjects);
                    } else return;
                }
            }

            for (Alien a : horde) {
                a.moveDown();
            }
        }
    }

    /**
     * @param gameObjects
     * @return an array with only aliens
     */

    private static Alien[] selectHorde(Shootable[] gameObjects) {
        int aliensAmount = 0;

        for (Shootable alien : gameObjects
        ) {
            if (alien instanceof Alien) {
                aliensAmount++;
            }
        }
        Alien[] result = new Alien[aliensAmount];
        for (int i = 0; i < result.length; i++) {
            result[i] = (Alien) gameObjects[i];
        }
        return result;
    }

    /**
     * updates the alien bound reference to the left
     *
     * @param aliens
     * @return updated alien reference
     */
    private static Alien alienReferenceLeft(Alien[] aliens) {
        Alien reference = aliens[0];
        if (!aliens[0].isActive() && !aliens[5].isActive() && !aliens[10].isActive()) {
            reference = aliens[1];
            if (!aliens[1].isActive() && !aliens[6].isActive() && !aliens[11].isActive()) {
                reference = aliens[2];
                if (!aliens[2].isActive() && !aliens[7].isActive() && !aliens[12].isActive()) {
                    reference = aliens[3];
                    if (aliens.length == 20 && !aliens[3].isActive() && !aliens[8].isActive() && !aliens[13].isActive()) {
                        reference = aliens[4];
                    }
                }
            }
        }
        return reference;
    }

    //TODO
    private static Alien alienReferenceBottom(Alien[] aliens) {
        Alien reference = aliens[aliens.length - 1];


        if (aliens.length == 20 && !aliens[15].isActive() && !aliens[16].isActive() && !aliens[17].isActive()) {
            reference = aliens[1];
            if (!aliens[1].isActive() && !aliens[6].isActive() && !aliens[11].isActive()) {
                reference = aliens[2];
                if (!aliens[2].isActive() && !aliens[7].isActive() && !aliens[12].isActive()) {
                    reference = aliens[3];

                }
            }
        }
        return reference;
    }

    /**
     * updates the alien bound reference to the right
     *
     * @param aliens
     * @return updated alien reference
     */
    private static Alien alienReferenceRight(Alien[] aliens) {
        Alien reference = aliens[aliens.length - 1];
        if (!aliens[14].isActive() && !aliens[4].isActive() && !aliens[9].isActive()) {
            reference = aliens[13];
            System.out.println(13);
            if (!aliens[13].isActive() && !aliens[8].isActive() && !aliens[3].isActive()) {
                reference = aliens[12];
                System.out.println(12);
                if (aliens[12].isActive() && aliens[7].isActive() && !aliens[2].isActive()) {
                    reference = aliens[11];
                    System.out.println(11);
                    if (aliens.length == 20 && !aliens[11].isActive() && !aliens[6].isActive() && !aliens[1].isActive()) {
                        reference = aliens[10];
                        System.out.println(10);

                    }
                }
            }
        }
        return reference;
    }

    private static boolean bossIsReady(Shootable[] gameObjects) {
        boolean bossAround = false;

        Alien[] aliens = selectHorde(gameObjects);

        if (deadCount == aliens.length) {
            return false;
        }

        deadCount = 0;
        for (Alien alien : aliens) {
            if (!alien.isActive()) {
                deadCount++;
            }
        }
        if (deadCount == aliens.length) {
            System.out.println("BOSS IS COMMING");
            return true;
        }
        System.out.println("BOSS IS NOT READY");
        return false;
    }
}


