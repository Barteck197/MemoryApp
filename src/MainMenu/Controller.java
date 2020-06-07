package MainMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Controller {
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
    private void gameSettingsScene(javafx.event.ActionEvent event) throws IOException {

        Parent board = FXMLLoader.load(getClass().getResource("board.fxml"));

        Scene boardScene = new Scene(board, Color.BLUE);

        Stage windowBoard = new Stage();
        windowBoard.setScene(boardScene);
        windowBoard.show();
    }

}