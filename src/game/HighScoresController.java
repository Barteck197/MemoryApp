package game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import static game.MenuController.resultListArray;

public class HighScoresController implements Exitable {

    @FXML
    ListView<Player> highScoresListView;

    //global high score list - to be displayed in the window
    static ObservableList<Player> resultList;
    //TODO sorting list by score

    static boolean initialized = false;

    public void listOldResults() throws FileNotFoundException {
        File file = new File("highScores.txt");
        if (file.exists() && !initialized) {
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
    }

    public void initialize() throws FileNotFoundException {
        //FIXME
        // - clean up the view for the user
        // - add scroll on too many results
        resultListArray.sort(new PlayerSorter());
        listOldResults();
        highScoresListView.setItems(resultList);
        initialized = true;
    }

    @FXML
    Button backButton;

    @FXML
    private void backToMenu() throws IOException {
        Stage window;
        Scene menuScene;

        menuScene = backButton.getScene();

        window = (Stage) menuScene.getWindow();

        menuScene = new Scene(FXMLLoader.load(getClass().getResource("scenes/mainMenu.fxml")));
        window.setScene(menuScene);
        window.setWidth(600);
        window.setHeight(400);
    }

    @Override
    public void exitApp() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
