package game.scenes;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Board extends GridPane {

    GridPane board = new GridPane();

    public Board(ActionEvent event) {
        Scene boardScene = new Scene(board);

        Stage windowBoardParams = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowBoardParams.setTitle("Game!!");
        windowBoardParams.setScene(boardScene);
        windowBoardParams.show();
    }
}