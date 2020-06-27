/*package game;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Card extends StackPane {
    //TODO implementacja i zrozumienie mechaniki
//    static int id = 1;
    private int clickCount = 2;
    private Card selected = null;


    private Text text = new Text();

    public Card(String value) {

        Rectangle border = new Rectangle(50, 50);
        border.setFill(null);
        border.setStroke(Color.BLACK);

//        this.setWidth(20);
//        this.setHeight(20);

        text.setText(value);
        text.setFont(Font.font(30));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border);

        setOnMouseClicked(event -> {
            if (isOpen() || clickCount == 0)
                return;

            clickCount--;

            if (selected == null) {
                selected = this;
                open(() -> {
                });
            } else {
                open(() -> {
                    if (!hasSameValue(selected)) {
                        selected.close();
                        this.close();
                    }

                    selected = null;
                    clickCount = 2;
                });
            }
        });
        close();
    }

    public boolean isOpen() {
        return text.getOpacity() == 1;
    }

    public void open(Runnable action) {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
        ft.setToValue(1);
        ft.setOnFinished(e -> action.run());
        ft.play();
    }

    public void close() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
        ft.setToValue(0);
        ft.play();
    }

    public boolean hasSameValue(Card other) {
        return text.getText().equals(other.text.getText());
    }
}
*/