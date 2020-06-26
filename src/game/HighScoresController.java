package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

import static game.MenuController.resultList;
import static game.MenuController.resultListArray;

public class HighScoresController implements Exitable {

    @FXML
    ListView<Player> highScoresListView;

    public void initialize() {
        //FIXME
        // - clean up the view for the user
        // - add scroll on too many results
        resultListArray.sort(new PlayerSorter());
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
