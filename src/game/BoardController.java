package game;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    BorderPane boardWindow;

    @FXML
    Pane gameBoard;

    private Card selected = null;
    private int clickCount = 2;

    private static final int pairs = (nrOfColumns * nrOfRows) / 2;

    public void initialize() throws FileNotFoundException {
        score = 10 * nrOfRows * nrOfColumns;
//        startTime();

        boardWindow.setPrefHeight(nrOfRows * 150);
        boardWindow.setPrefWidth(nrOfColumns * 150);

        //stworzenie listy par Kart

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < pairs; i++) {
            cards.add(
                    new Card("./src/game/img/p" + (i + 1) + ".jpeg", i)
            );
            cards.add(
                    new Card("./src/game/img/p" + (i + 1) + ".jpeg", i)
            );
        }

        Collections.shuffle(cards);

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            card.setTranslateX(100 * (i % nrOfRows));
            card.setTranslateY(100 * (i / nrOfRows));
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
        //        Text text = new Text();
        private ImageView cardImageView;
        private final int cardID;

        public Card(String path, int cardID) throws FileNotFoundException {

            //setting up graphics of the card
            Image cardImage = new Image(new FileInputStream(path));
            cardImageView = new ImageView(cardImage);

            cardImageView.setFitHeight(100);
            cardImageView.setFitWidth(100);
            cardImageView.setPreserveRatio(true);

            //setting cardID - necessary to check if pair.
            this.cardID = cardID;

            Rectangle border = new Rectangle(100, 100);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            border.setStrokeWidth(5);

            setAlignment(Pos.CENTER);
            getChildren().addAll(cardImageView, border);
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
            return cardImageView.getOpacity() == 1;
        }

        public void open(Runnable action) {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), cardImageView);
            ft.setToValue(1);
            ft.setOnFinished(e -> action.run());
            ft.play();
        }

        public void close() {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), cardImageView);
            ft.setToValue(0);
            ft.play();
        }

        public boolean hasSameValue(Card other) {
            return cardID == other.cardID;
        }
    }
}