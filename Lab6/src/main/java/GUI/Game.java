package GUI;

import Controlers.ControlerButtons;
import data.Board;
import data.Position;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Game extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static Board gameBoard;
    private static Scene sc;

    public static Scene getSc() {
        return sc;
    }

    public static void setSc(Scene sc) {
        Game.sc = sc;
    }

    public static Board getGameBoard() {
        return gameBoard;
    }

    public static void setGameBoard(Board gameBoard) {
        Game.gameBoard = gameBoard;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/GUI.fxml"));
        gameBoard = new Board();

        Parent root = loader.load();
        sc = new Scene(root);


        primaryStage.setScene(sc);
        primaryStage.setTitle("Lab6-Game");
        primaryStage.show();


    }
}