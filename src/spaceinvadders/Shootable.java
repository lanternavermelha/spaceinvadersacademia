package spaceinvadders;

public interface Shootable {

    boolean isVisible();

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    void kill();

    int getSpeed();

    void shoot(Shootable[] shootables);

    void hit();
}
