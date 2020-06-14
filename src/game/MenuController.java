package game;

import game.Card;
import game.scenes.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class MenuController {

    //tracing time
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;
    static boolean state = true;
    @FXML
    Label appTime;

    @FXML
    private void startTime() {
        state = true;
        Thread t = new Thread() {
            @Override
            public void run() {
                while (state) {
                    try {
                        Thread.sleep(1000);

                        if (seconds >= 60) {
                            seconds = 0;
                            minutes++;
                        }
                        if (minutes >= 60) {
                            seconds = 0;
                            minutes = 0;
                            hours++;
                        }

                        seconds++;

//                        appTime.setText(minutes + " : " + seconds);
                        System.out.println(hours + ":" + minutes + ":" + seconds);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    @FXML
    Button gameSettings;

    //creating scenes
    @FXML
    private void gameSettingsScene(ActionEvent event) throws IOException {

        Stage window;
        Scene boardSettings;

        boardSettings = gameSettings.getScene();

        window = (Stage) boardSettings.getWindow();

        boardSettings = new Scene(FXMLLoader.load(getClass().getResource("scenes/boardSettings.fxml")));
        window.setScene(boardSettings);

    }

    @FXML
    private void highScoresTable(ActionEvent event) throws IOException {
        Parent highScoreBoard = FXMLLoader.load(getClass().getResource("scenes/highScores.fxml"));
        Scene highScoreScene = new Scene(highScoreBoard);

        Stage windowHighScores = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowHighScores.setTitle("High Scores");
        windowHighScores.setScene(highScoreScene);
        windowHighScores.show();
    }

    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("scenes/mainMenu.fxml"));
        Scene menu = new Scene(mainMenu, 600, 400);

        Stage windowHighScores = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowHighScores.setTitle("High Scores");
        windowHighScores.setScene(menu);
        windowHighScores.show();
    }

    @FXML
    Button closeButton;

    @FXML
    private void exitGame() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        Parent boardSettings = FXMLLoader.load(getClass().getResource("scenes/board.fxml"));
        Scene boardScene = new Scene(boardSettings);

        Stage windowBoardParams = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowBoardParams.setTitle("Game!!");
        windowBoardParams.setScene(boardScene);
        windowBoardParams.show();
    }
//
//    private void startClassyGame(){
//        Board game = new Board(ActionEvent);
//    }


    //game settings
    @FXML
    TextField rows;

    @FXML
    TextField columns;

    public int getrows() {
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