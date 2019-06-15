package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class ProtectionBlock implements Shootable {

    private int hitPoints;
    private Picture representation;
    private boolean active;
    private int positionY;

    public ProtectionBlock(int x) {
        positionY = 450;
        hitPoints = 300;

        representation = new Picture(x, positionY, "resources/laserGreen.png");
        representation.draw();
        active = true;
    }

    public void hit() {
        hitPoints -= 50;
        if (hitPoints == 0) {
            kill();
        }
        changeRepresentation();
    }

    private void changeRepresentation() {
        if (hitPoints == 200) {
            representation.load("resources/laserOrange.png");
        }
        if (hitPoints == 100) {
            representation.load("resources/laserRed.png");
        }
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
        active = false;
        representation.delete();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "ProtectionBlock";
    }

}
