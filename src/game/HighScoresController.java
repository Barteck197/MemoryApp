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

public class HighScoresController implements Exitable {

    @FXML
    ListView<Player> highScoresListView;

    ObservableList<Player> resultList;

    public void initialize() throws FileNotFoundException {
        //TODO clean up the view for the user
        highScoresListView.setItems(listOldResults());

        //TODO add scroll on too many results
    }

    public ObservableList<Player> listOldResults() throws FileNotFoundException {
        resultList = FXCollections.observableArrayList();

        File file = new File("highScores.txt");
        if (file.exists()) {
            FileInputStream oldResults = new FileInputStream(file);

            boolean proceed = true;

            try {
                ObjectInputStream input = new ObjectInputStream(oldResults);
                while (proceed) {
                    //TODO deserialize more than one object
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
            return resultList;
        } else {
            return null;
        }
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
