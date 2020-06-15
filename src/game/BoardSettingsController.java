package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class BoardSettingsController {

    @FXML
    private void startGame(ActionEvent event) throws IOException {

        if ((getColumns() * getRows()) % 2 == 0) {
            Parent boardSettings = FXMLLoader.load(getClass().getResource("scenes/board.fxml"));
            Scene boardScene = new Scene(boardSettings);

            Stage windowBoardParams = (Stage) ((Node) event.getSource()).getScene().getWindow();
            windowBoardParams.setTitle("Game!!");
            windowBoardParams.setScene(boardScene);
            windowBoardParams.show();
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

    public int getRows() {
        return Integer.parseInt(rows.getText());
    }

    public int getColumns() {
        return Integer.parseInt(columns.getText());
    }

    //TODO input validation: rows x columns % 2 == 0

    @FXML
    private GridPane gameBoard;
    private ImageIcon card;

    public static GridPane board(int rows, int columns) {

        GridPane board = new GridPane();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                Card card = new Card();

                //TODO dodawanie grafiki karty
                board.add(card, i, j);
                GridPane.setMargin(card, new Insets(5));
            }
        }
        return board;
    }

}
