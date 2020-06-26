package game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static game.BoardController.score;

public class ScoreSaverController implements Exitable {

    //FIXME result mechanics:
    // - serialize list instead of single object

    @FXML
    Button saveResult;

    @FXML
    TextField playerName;

    //global high score list
    static ObservableList<Player> resultList;

    public void saveResult() throws IOException {
        resultList = FXCollections.observableArrayList();

        if (playerName.getText().equals("")) {
            alertWrongInput();
        } else {
            Player pl = new Player(playerName.getText(), score);

            //adding player to global high score list
            resultList.add(pl);

            FileOutputStream fs;
            ObjectOutputStream os = null;

            try {
                fs = new FileOutputStream("highScores.txt", true);
                os = new ObjectOutputStream(fs);
                os.writeObject(new ArrayList<>(resultList));
//                os.writeObject(resultList);
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
            cleanUserInput();
            exitApp();
        }


    }

    public void cleanUserInput(){
        playerName.setText("");
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);

        al.setTitle("OK");
        al.setHeaderText("Dziękujemy, zapisano");
        al.show();
    }

    public void alertWrongInput() {
        Alert al = new Alert(Alert.AlertType.WARNING);

        al.setTitle("Błąd");
        al.setHeaderText("Podaj swoje imię");
        al.show();
    }

    @Override
    public void exitApp() {
        Stage stage = (Stage) saveResult.getScene().getWindow();
        stage.close();
    }
}
