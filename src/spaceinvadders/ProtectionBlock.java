package spaceinvadders;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class ProtectionBlock implements Shootable {
    private int hitpoints;
    private Picture representation;
    private boolean visible;

    //TODO -- y and speed are not needed in this class!
    public ProtectionBlock(int x, int y, int speed) {
        hitpoints = 300;
        representation = new Picture(x, 450, "/Users/albertoreis/dev/spaceinvadersgroup/resources/laserGreen.png");
        representation.draw();
        visible=true;
    }

    public void hit() {
        hitpoints -= 50;
        if (hitpoints==0){
            kill();
        }

        changeRepresentation();

    }

    private void changeRepresentation() {
        if (hitpoints == 200) {
            representation.load("/Users/albertoreis/dev/spaceinvadersgroup/resources/laserOrange.png");
        }
        if (hitpoints == 100) {
            representation.load("/Users/albertoreis/dev/spaceinvadersgroup/resources/laserRed.png");
        }
    }


    @Override
    public void shoot(Shootable[] shootables) { }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public boolean isVisible() {
        return visible;
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
        visible = false;
        representation.delete();
    }
    @Override
    public String toString() {
        return "ProtectionBlock";
    }

}
