package board;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage boardStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("board.fxml"));
        boardStage.setTitle("Hello World");
        boardStage.setScene(new Scene(root, 600, 400));
        boardStage.show();

        boardStage.setOnCloseRequest(windowEvent -> Controller.state = false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
