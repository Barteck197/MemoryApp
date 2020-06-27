package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BoardSettingsController implements Exitable {

    @FXML
    private void startGame() throws IOException {

        setNrOfColumns();
        setNrOfRows();

        if ((nrOfColumns * nrOfRows) % 2 == 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/board.fxml"));
            Parent root = loader.load();

            Stage window = new Stage();
            window.setScene(new Scene(root));
            window.setMinWidth((6*nrOfColumns)+250);
            window.setMinHeight((6*nrOfRows)+250);
            window.show();
        } else {
            alertWrongInput();
        }
    }


    public void alertWrongInput() {
        Alert al = new Alert(Alert.AlertType.WARNING);

        al.setTitle("Błąd");
        al.setHeaderText("Nie można stworzyć planszy");
        al.setContentText("Z wartości, które podałeś nie można stworzyć planszy." +
                "\nIloczyn liczb wierszy i kolumn w planszy musi być liczbą parzystą.");
        al.show();
    }

    public void alertWrongNumber() {
        Alert al = new Alert(Alert.AlertType.WARNING);

        al.setTitle("Błąd");
        al.setHeaderText("Nie można stworzyć planszy");
        al.setContentText("Z wartości, które podałeś nie można stworzyć planszy." +
                "\nPodałeś zbyt duże wartości.");
        al.show();
    }

    public void alertInputNotNumber() {
        Alert al = new Alert(Alert.AlertType.WARNING);

        al.setTitle("Błąd");
        al.setHeaderText("Nie można stworzyć planszy");
        al.setContentText("Z wartości, które podałeś nie można stworzyć planszy." +
                "\nPodaj liczby.");
        al.show();
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

    //game settings

    @FXML
    TextField rows;

    @FXML
    TextField columns;

    //TODO czy te zmienne na pewno muszą być statyczne?
    public static int nrOfRows;
    public static int nrOfColumns;

    //walidacja inputu użytkownika
    public void setNrOfRows() {
        if (rows.getText().equals("")) {
            alertInputNotNumber();
        } else {
            nrOfRows = Integer.parseInt(rows.getText());
        }

        //TODO poprawna konfiguracja regexów
        /*if (rows.getText().matches("/d+")) {
            if (rows.getText().matches("\b([1-9]|1[0-6])\b")) {
                nrOfRows = Integer.parseInt(rows.getText());
            } else {
                alertWrongNumber();
            }
        } else {
            alertInputNotNumber();
        }*/
    }

    public void setNrOfColumns() {
        if (columns.getText().equals("")) {
            alertInputNotNumber();
        } else {
            nrOfColumns = Integer.parseInt(columns.getText());
        }

        //TODO poprawna konfiguracja regexów
        /*
        if (rows.getText().matches("/d+")) {
            if (rows.getText().matches("\b([1-9]|1[0-6])\b")) {
                nrOfColumns = Integer.parseInt(columns.getText());
            } else {
                alertWrongNumber();
            }
        } else {
            alertInputNotNumber();
        }
        */
    }

    @Override
    public void exitApp() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}