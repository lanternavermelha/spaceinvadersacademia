package spaceinvadders;

public interface Shootable {

    boolean isActive();

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    void kill();

    void hit();
}
