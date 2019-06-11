import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Alien extends Character {

    private boolean dead;
    private boolean moveRight;
    private boolean moveLeft;
    Picture representation;
    int speed1;


    public Alien(int x, int y, int speed) {
        super(x, y, speed);
        speed1 = speed;

        double ran = Math.random();
        if (ran < .4) {
            representation = new Picture(x, y, "Resources/invaderRed.png");
        }
        if (ran >= .4 && ran < .7) {
            representation = new Picture(x, y, "Resources/invaderBlue.png");
        }
        if (ran >= .7){
            representation = new Picture(x, y, "Resources/invaderGreen.png");
        }
        representation.draw();
    }

    @Override
    public void shoot() {
    }

    public void kill() {
        dead = true;
        representation.delete();
    }


    public boolean isDead() {
        return dead;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void moveDown() {
        representation.translate(0, speed1 * 2);

    }

    public void moveUp() {
        representation.translate(0, -speed1);
    }

    public void moveRight() {
        representation.translate(speed1, 0);
    }

    public void moveLeft() {
        representation.translate(-speed1, 0);
    }
}

