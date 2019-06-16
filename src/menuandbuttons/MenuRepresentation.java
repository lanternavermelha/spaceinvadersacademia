package menuandbuttons;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MenuRepresentation {

    private Picture menuBackground;
    private Picture menuLoading;
    private Picture instructions;
    private Picture credits;


    public MenuRepresentation() {
        this.menuBackground = new Picture(10, 10, "resources/background_good.png");
        this.menuLoading = new Picture(10,10, "resources/loading_actual.png");
        this.instructions = new Picture(10,10, "resources/background_instructions.png");
        this.credits = new Picture(10,10, "resources/background_credits.png");

    }

    public void init() throws InterruptedException {
        menuLoading.draw();
        Thread.sleep(4000);
        menuLoading.delete();
    }

    public void mainMenu() {
        menuBackground.draw();
    }

    public void instructions() {
        instructions.draw();
    }

    public void credits() {
        credits.draw();
    }

    public void creditsDelete() {
        credits.delete();
    }

    public void instructionsDelete() {
        instructions.delete();
    }
}


