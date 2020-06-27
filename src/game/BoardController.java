package game;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static game.BoardSettingsController.nrOfColumns;
import static game.BoardSettingsController.nrOfRows;

public class BoardController implements Exitable {

    //tracing time
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;
    static boolean state;

    //score
    static int score;

    @FXML
    Text appTime;

    //TODO tracing time
    @FXML
    private void startTime() {
        state = true;

        Platform.runLater(() -> {
            while (state) {
                try {
                    Thread.sleep(1000);
                    /*
                    if (seconds >= 60) {
                        seconds = 0;
                        minutes++;
                    }
                    if (minutes >= 60) {
                        seconds = 0;
                        minutes = 0;
                        hours++;
                    }
                    */
                    seconds++;

                    appTime.setText(String.valueOf(seconds));
                    System.out.println(seconds);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        /*Thread t = new Thread() {
            @Override
            public void run() {
                while (state) {
                    try {
                        Thread.sleep(1000);

                        if (seconds >= 60) {
                            seconds = 0;
                            minutes++;
                        }
                        if (minutes >= 60) {
                            seconds = 0;
                            minutes = 0;
                            hours++;
                        }

                        seconds++;

//                        appTime.setText(minutes + " : " + seconds);
                        System.out.println(hours + ":" + minutes + ":" + seconds);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();*/
    }


    @FXML
    Pane gameBoard;

    private Card selected = null;
    private int clickCount = 2;

    private static final int pairs = (nrOfColumns * nrOfRows) / 2;

    public void initialize() {
        score = 10 * nrOfRows * nrOfColumns;
//        startTime();
//        gameBoard.setPrefHeight((6 * nrOfRows) + 150);
//        gameBoard.setPrefHeight((6 * nrOfColumns) + 150);
//
//        boardWindow.setPrefHeight((6 * nrOfRows) + 200);
//        boardWindow.setPrefWidth((6 * nrOfColumns) + 200);

        //stworzenie listy par Kart

        char c = 'A';
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < pairs; i++) {
            cards.add(new Card(String.valueOf(c)));
            cards.add(new Card(String.valueOf(c)));
            c++;
        }

        Collections.shuffle(cards);

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            card.setTranslateX(50 * (i % nrOfRows));
            card.setTranslateY(50 * (i / nrOfRows));
            gameBoard.getChildren().add(card);
        }

    }

    //TODO exiting with key combination
    public void exitWithKey(KeyEvent ke) {

        KeyCombination exitComb = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);

        if (exitComb.match(ke)) {
            Stage stage = (Stage) gameBoard.getScene().getWindow();
            stage.close();
            state = false;
            System.out.println("CTRL + ALT");
        }
    }

    public void endGame() throws IOException {
        state = false;
        Stage window;
        Scene menuScene;

        menuScene = appTime.getScene();

        window = (Stage) menuScene.getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/saveScore.fxml"));

        menuScene = new Scene(loader.load());
        window.setScene(menuScene);
        window.setWidth(600);
        window.setHeight(400);
    }

    @Override
    public void exitApp() {
        state = false;
        Stage stage = (Stage) appTime.getScene().getWindow();
        stage.close();
    }

    private class Card extends StackPane {
        Text text = new Text();

        public Card(String value) {

            Rectangle border = new Rectangle(50, 50);
            border.setFill(null);
            border.setStroke(Color.BLACK);


            text.setText(value);
            text.setFont(Font.font(30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
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
}