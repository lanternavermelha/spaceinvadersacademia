import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public abstract class Character {

    private int x;
    private int y;
    private int speed;

    public Character(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void shoot();

}
