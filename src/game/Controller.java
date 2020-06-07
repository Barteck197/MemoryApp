package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

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
    private void gameSettingsScene() throws IOException {

        Parent boardSettings = FXMLLoader.load(getClass().getResource("scenes/boardSettings.fxml"));
        Scene boardScene = new Scene(boardSettings);

        Stage windowBoardParams = new Stage();
        windowBoardParams.setTitle("Game parameters");
        windowBoardParams.setScene(boardScene);
        windowBoardParams.show();
    }

    @FXML
    private void highScoresTable() throws IOException{
        Parent highScoreBoard = FXMLLoader.load(getClass().getResource("scenes/highScores.fxml"));
        Scene highScoreScene = new Scene(highScoreBoard);

        Stage windowHighScores = new Stage();
        windowHighScores.setTitle("High Scores");
        windowHighScores.setScene(highScoreScene);
        windowHighScores.show();

    }

    @FXML
    Button closeButton;

    @FXML
    private void exitGame() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}