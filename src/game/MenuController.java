package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

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
    Button gameSettings;

    //creating scenes
    @FXML
    private void gameSettingsScene() throws IOException {
        Stage window;
        Scene boardSettings;

        boardSettings = gameSettings.getScene();

        window = (Stage) boardSettings.getWindow();

        boardSettings = new Scene(FXMLLoader.load(getClass().getResource("scenes/boardSettings.fxml")));
        window.setScene(boardSettings);
    }

    @FXML
    Button highScores;

    @FXML
    private void highScoresTable() throws IOException {
        Stage window;
        Scene highScoreScene;

        highScoreScene = highScores.getScene();

        window = (Stage) highScoreScene.getWindow();

        highScoreScene = new Scene(FXMLLoader.load(getClass().getResource("scenes/highScores.fxml")));
        window.setScene(highScoreScene);
    }

    @FXML
    Button closeButton;

    @FXML
    private void exitGame() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}