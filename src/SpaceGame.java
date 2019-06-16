import menuandbuttons.Menu;
import spaceinvadders.Field;
import spaceinvadders.GameLevel;

public class SpaceGame {
    public static void main(String[] args) throws InterruptedException {

        Field field = new Field(GameLevel.INSANE);

        //TODO delay is doing something to the game ?

        Menu menu = new Menu();
        menu.menuSelection();

        field.init();
    }
}