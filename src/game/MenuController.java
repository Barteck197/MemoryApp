package game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class MenuController implements Exitable {

    //global high score list - to be displayed in the window
    static ObservableList<Player> resultList;

    static boolean initialized = false;

    public void initialize() throws FileNotFoundException {
        if (!initialized) {
            File file = new File("highScores.txt");
            if (file.exists()) {
                FileInputStream oldResults = new FileInputStream(file);
                try {
                    ObjectInputStream input = new ObjectInputStream(oldResults);
                    resultListArray.addAll((ArrayList<Player>) input.readObject());
                    resultList = FXCollections.observableList(resultListArray);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                resultList = FXCollections.observableList(resultListArray);
            }
            initialized = true;
        } else {
            initialized = true;
        }
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

    //create and initialize global high score list
    static ArrayList<Player> resultListArray = new ArrayList<>();

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

    @Override
    public void exitApp() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}