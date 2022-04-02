package GUI;

import data.Board;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Game extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private Board gameBoard;

    /*@FXML
    private GridPane grid;
    @FXML
    private void CreateButtonAction(ActionEvent event){
        event.consume();
        gameBoard.generateBoard();

    }*/


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/GUI/GUI_Style.fxml"));

        Parent root=loader.load();
        Scene sc=new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Lab6-Game");
        primaryStage.show();
    }

}