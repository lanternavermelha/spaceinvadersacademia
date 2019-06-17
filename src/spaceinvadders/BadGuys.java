package spaceinvadders;

abstract class BadGuys implements Shootable {


    private GameLevel gameLevel;

    public abstract GameLevel getGameLevel();

    public abstract void shoot(Shootable[] shootables);
}
