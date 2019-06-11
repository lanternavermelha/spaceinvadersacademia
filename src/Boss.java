import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Boss extends Character {

    private boolean dead;
    private boolean moveRight;
    private boolean moveLeft;
    Picture representation;

    public Boss(int x, int y, int speed) {
        super(x, y, speed);
        representation = new Picture(x, y, "Resources/super.png");
        // representation.setColor(Color.RED);
        representation.draw();
    }

    @Override
    public void shoot() {

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

    public void moveRight() {
        representation.translate(representation.getX() + 1, 0);
    }



    public void moveLeft() {


        representation.translate(representation.getX() - 10, 0);

    }

    public void kill(){
        dead = true;
        representation.delete();
    }

    public boolean isDead() {
        return dead;
    }
}