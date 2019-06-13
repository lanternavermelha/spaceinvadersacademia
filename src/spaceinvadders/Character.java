package spaceinvadders;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public abstract class Character {
    //Changed

    public abstract boolean isDead();

    public abstract int getX();

    public abstract int getY();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void kill();

    public abstract int getSpeed();

    public abstract void shoot(Character[] characters);

}
