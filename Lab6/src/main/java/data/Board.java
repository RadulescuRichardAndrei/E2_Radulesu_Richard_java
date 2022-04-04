package data;

import java.io.Serializable;

public class Board implements Serializable {

    private Position[][] table;
    private int size;

    public Board() {
    }

    public Board(Position[][] table, int size) {
        this.table = table;
        this.size = size;
    }

    public Position[][] getTable() {
        return table;
    }

    public void setTable(Position[][] table) {
        this.table = table;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public void generateBoard(int size) {
        setSize(size);
        table = new Position[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                table[i][j] = new Position();
        //border
        for (int i = 0; i < size - 1; i++) {
            if (Math.random() <= 0.5) {
                table[0][i].setRight(true);
                table[0][i + 1].setLeft(true);
            }
            if (Math.random() <= 0.5) {
                table[0][i].setRight(true);
                table[0][i + 1].setLeft(true);
            }
            if (Math.random() <= 0.5) {
                table[i][0].setDown(true);
                table[i + 1][0].setUp(true);
            }
        }
        //inside
        for (int i = 1; i < size - 1; i++)
            for (int j = 1; j < size - 1; j++){
                if (Math.random() <= 0.5) {
                    table[i][j].setRight(true);
                    table[i][j + 1].setLeft(true);
                }
                if (Math.random() <= 0.5) {
                    table[i][j].setLeft(true);
                    table[i][j - 1].setRight(true);
                }
                if (Math.random() <= 0.5) {
                    table[i][j].setDown(true);
                    table[i+1][j].setUp(true);
                }
                if (Math.random() <= 0.5) {
                    table[i][j].setUp(true);
                    table[i-1][j].setDown(true);
                }
            }


    }

}
