package spaceinvadders;

abstract class BadGuys implements Shootable {


    private int speed;

    public abstract int getSpeed();

    public abstract void shoot(Shootable[] shootables);
}
