package menuandbuttons;


public enum ButtonType {
    PLAY(170, 215, 383, 278),
    INSTRUCTIONS(72, 294, 488, 340),
    CREDITS(152, 361, 406, 416),
    BACK(37, 540, 175, 590);

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    ButtonType(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
