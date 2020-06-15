package game;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
        startTime();

    }

    public void exitWithKey(){

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
}
