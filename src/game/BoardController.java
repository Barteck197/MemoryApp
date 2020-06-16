package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BoardController {

    //tracing time
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;
    static boolean state = true;
    @FXML
    Label appTime;

    @FXML
    private void startTime() {
        state = true;
        Thread t = new Thread() {
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
        t.start();
    }


    @FXML
    GridPane gameBoard;

    public void initialize() {
//        startTime();

    }

    //TODO exiting with keyboard shortcuts
    public void exitWithKey(KeyEvent ke) {

        KeyCombination exitComb = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);

        if (exitComb.match(ke)) {
            Stage stage = (Stage) gameBoard.getScene().getWindow();
            stage.close();
            state = false;
            System.out.println("CTRL + ALT");
        }
    }

    public static GridPane board(int rows, int columns) {

        GridPane board = new GridPane();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                Card card = new Card();

                //TODO dodawanie grafiki karty
                board.add(card, i, j);
                GridPane.setMargin(card, new Insets(5));
            }
        }
        return board;
    }



    public void endGame() throws IOException {
        Stage window;
        Scene menuScene;

        menuScene = appTime.getScene();

        window = (Stage) menuScene.getWindow();

        menuScene = new Scene(FXMLLoader.load(getClass().getResource("scenes/saveScore.fxml")));
        window.setScene(menuScene);
        window.setWidth(600);
        window.setHeight(400);
    }

}
