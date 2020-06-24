package game;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HighScoresController implements Exitable {

    @FXML
    ListView<Player> highScoresListView;

    ObservableList<Player> resultList;

    public void initialize() throws FileNotFoundException {
        resultList = FXCollections.observableArrayList();

        //TODO check if file exists
        FileInputStream oldResults = new FileInputStream("highScores.txt");

        boolean proceed = true;

        try {
            ObjectInputStream input = new ObjectInputStream(oldResults);
            while (proceed) {
                //TODO deserialize multiple objects
                Player pl = (Player) input.readObject();
                if (pl != null) {
                    resultList.add(pl);
                } else {
                    proceed = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO clean up the view for the user
        highScoresListView.setItems(resultList);
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
