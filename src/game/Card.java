package game;

import javafx.scene.shape.Rectangle;

public class Card extends Rectangle {
    int height = 20;
    int width = 20;
    static int id = 1;

    public Card() {
        this.setWidth(20);
        this.setHeight(20);
        this.setId(String.valueOf(id));
        id++;
    }
}
