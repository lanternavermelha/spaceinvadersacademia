import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player extends Character {

    private boolean dead;
    Picture ship;
    Rectangle bullet;
    int speed1;
    int playerLevel;
    int aliensKilled;

    public Player(int x, int y, int speed) {
        super(x, y, speed);
        ship = new Picture(Field.getWIDTH() / 2, Field.getHEIGHT() - 100, "Resources/ship.png");
        ship.draw();
        speed1 = speed;
    }


    @Override
    public void shoot() {
        bullet = new Rectangle(ship.getX() + ship.getWidth() / 2, ship.getY(), 3, 6);
        bullet.setColor(Color.YELLOW);
        bullet.fill();

        while (bullet.getY() > 20) {
            bullet.translate(0, -20);
        }
    }

    public void moveDown() {
        ship.translate(0, 20);
    }

    public void moveUp() {
        // representation.translate(0, -20);
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public int getAliensKilled() {
        return aliensKilled;
    }

    public void moveRight() {
        ship.translate(speed1, 0);
    }

    public void moveLeft() {
        ship.translate(-speed1, 0);
    }

    public void hitChecker(Alien[] aliens) {
        for (int i = aliens.length - 1; i >= 0; i--) {
            if (!aliens[i].isDead()) {
                if (bullet.getY() == aliens[i].representation.getY() + aliens[i].representation.getHeight()//To get the bottom of the figure
                        || bullet.getX() <= aliens[i].representation.getX() + aliens[i].representation.getWidth()
                        && bullet.getX() >= aliens[i].representation.getX()) {
                    aliens[i].kill();
                    System.out.println("alien " + i + " is dead");
                    aliensKilled++;
                    System.out.println("I killed: " + aliensKilled);
                    if(aliensKilled == aliens.length){
                        playerLevel++;
                        System.out.println("Level up! "+playerLevel);
                    }
                    break;
                }
            }
        }
    }
}