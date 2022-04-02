package GUI;

import data.Board;
import data.Position;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardGui {
    private GridPane grid;
    private Button[][] myNodes;
    private int size;

    public BoardGui(@NamedArg("size") int size) {
        this.size = size;


        for (Button[] b : myNodes) {
            b = new Button[]{new Button()};
        }
        grid.setHgap(10);
        grid.setVgap(10);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                grid.add(myNodes[i][j],i,j);
            }
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BoardGui(Board boardData, GridPane grid, Button[][] myNodes) {

        this.grid = grid;
        this.myNodes = myNodes;
    }


    public GridPane getGrid() {
        return grid;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public Button[][] getMyNodes() {
        return myNodes;
    }

    public void setMyNodes(Button[][] myNodes) {
        this.myNodes = myNodes;
    }
}
