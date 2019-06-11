import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Bullet {

    Rectangle rectangle;
    private int x;
    private int y;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        rectangle = new Rectangle(x,y,3,7);

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
