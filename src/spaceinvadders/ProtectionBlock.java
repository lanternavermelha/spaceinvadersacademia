package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class ProtectionBlock extends Character {
    private int hitpoints;
    Picture representation;

    //TODO -- y and speed are not needed in this class!
    public ProtectionBlock(int x, int y, int speed) {
        hitpoints = 300;
        representation = new Picture(x, 450, "/Users/albertoreis/dev/spaceinvadersgroup/resources/laserGreen.png");
        representation.draw();
    }

    public void hit() {
        hitpoints -= 50;

        changeRepresentation();
    }

    public void changeRepresentation() {
        if (hitpoints == 200) {
            representation.delete();
            representation = new Picture(getX(), 450, "/Users/albertoreis/dev/spaceinvadersgroup/resources/laserOrange.png");
            representation.draw();
        }
        if (hitpoints == 100) {
            representation.delete();
            representation = new Picture(getX(), 450, "/Users/albertoreis/dev/spaceinvadersgroup/resources/laserRed.png");
            representation.draw();
        }
    }


    @Override
    public void shoot(Character[] characters) {
//TODO this object does not shoot--must think a better way to not implement this
    }

    @Override
    public int getSpeed() {
        return 0;
    }



    @Override
    public boolean isDead() {
        return hitpoints <= 0;
    }

    @Override
    public int getX() {
        return representation.getX();
    }

    @Override
    public int getY() {
        return representation.getY();
    }

    @Override
    public int getWidth() {
        return representation.getWidth();
    }

    @Override
    public int getHeight() {
        return representation.getHeight();
    }

    @Override
    public void kill() {
        //TODO -- kill it
        representation.delete();
    }
    @Override
    public String toString() {
        return "ProtectionBlock";
    }



}
