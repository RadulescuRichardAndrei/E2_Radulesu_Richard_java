package Controlers;

import GUI.Game;
import data.Board;
import data.Position;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javax.imageio.ImageIO;
import java.io.*;

public class ControlerButtons {

    private int lastX=-1, lastY=-1;
    private boolean gameFinished=false;

    @FXML
    private Label winner;
    @FXML
    private TextField sizeInput;
    @FXML
    private GridPane myGrid;

    @FXML
    private void ValueChange() {

        if (sizeInput.getText().matches("[0-9]+")) {
            int value = Integer.valueOf(sizeInput.getText());
            value = value % 13;
            if (value < 5)
                value = 5;
            Game.getGameBoard().setSize(value);
        }
    }

    private Node getNodeFromGrid(int row, int col) {

        for (Node node : myGrid.getChildren()) {
            if (myGrid.getColumnIndex(node) == col && myGrid.getRowIndex(node) == row)
                return node;
        }
        return null;
    }
    private void checkComputerWin(){
        Position[][] positions=Game.getGameBoard().getTable();

        if(! ((positions[lastX][lastY].isUp() && positions[lastX-1][lastY].getPlayer()==0 ) ||
            (positions[lastX][lastY].isDown() && positions[lastX+1][lastY].getPlayer()==0 ) ||
            (positions[lastX][lastY].isLeft() && positions[lastX][lastY-1].getPlayer()==0 ) ||
            (positions[lastX][lastY].isRight() && positions[lastX][lastY+1].getPlayer()==0 ))){
            gameFinished=true;
            winner.setText("Calculatorul castiga");

        }

    }
    private boolean checkPlayerMove(int posx,int posy){
        Position[][] positions=Game.getGameBoard().getTable();
        if( (posx == lastX-1 && posy==lastY && positions[posx][posy].isDown()) || (posx == lastX+1 && posy==lastY && positions[posx][posy].isUp()) ||
                (posx == lastX && posy==lastY-1 && positions[posx][posy].isRight()) || (posx == lastX && posy==lastY+1 && positions[posx][posy].isLeft()))
            return true;
        return false;
    }

    private void playerMove(int posx,int posy){
        Position[][] positions=Game.getGameBoard().getTable();
        positions[posx][posy].setPlayer(1);


        if(positions[posx][posy].isRight() && positions[posx][posy+1].getPlayer()==0){
            lastX=posx;
            lastY=posy+1;
            positions[lastX][lastY].setPlayer(2);
            Button neighbour= (Button) getNodeFromGrid(lastX*2,lastY*2);
            neighbour.setStyle("-fx-background-color: #7417d1");
            checkComputerWin();
        }else
        if(positions[posx][posy].isLeft() && positions[posx][posy-1].getPlayer()==0){
            lastX=posx;
            lastY=posy-1;
            positions[lastX][lastY].setPlayer(2);
            Button neighbour= (Button) getNodeFromGrid(lastX*2,lastY*2);
            neighbour.setStyle("-fx-background-color: #7417d1");
            checkComputerWin();
        }else
        if(positions[posx][posy].isDown() && positions[posx+1][posy].getPlayer()==0){
            lastX=posx+1;
            lastY=posy;
            positions[lastX][lastY].setPlayer(2);
            Button neighbour= (Button) getNodeFromGrid(lastX*2,lastY*2);
            neighbour.setStyle("-fx-background-color: #7417d1");
            checkComputerWin();
        }else
        if(positions[posx][posy].isUp() && positions[posx-1][posy].getPlayer()==0){
            lastX=posx-1;
            lastY=posy;
            positions[lastX][lastY].setPlayer(2);
            Button neighbour= (Button) getNodeFromGrid(lastX*2,lastY*2);
            neighbour.setStyle("-fx-background-color: #7417d1");
            checkComputerWin();
        }
        else{
            gameFinished=true;
            winner.setText("Jucatorul a castigat");
        }
    }

    private void addFunctionalityButton(){

        Position[][] positions=Game.getGameBoard().getTable();
        for(Node node:myGrid.getChildren())
            if(node instanceof Button){
                Button b=(Button) node;
                int posx = myGrid.getRowIndex(node)/2;
                int posy=myGrid.getColumnIndex(node)/2;


                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //player has first move
                        if(!gameFinished && positions[posx][posy].getPlayer()==0 &&
                                (lastX+lastY<0 || (lastX+lastY>0 && checkPlayerMove(posx,posy)))){

                            b.setStyle("-fx-background-color: #17d117");
                            playerMove(posx,posy);
                        }
                     }
                });
            }

    }

    private void drawEdges(){
        Position[][] pos= Game.getGameBoard().getTable();

        for (int i = 0; i < Game.getGameBoard().getSize(); i++)
            for (int j = 0; j < Game.getGameBoard().getSize(); j++) {
                Button b1 = new Button(i + " " + j);

                if(pos[i][j].isRight()){
                    Label l1=new Label("----");
                    l1.setTextAlignment(TextAlignment.CENTER);
                    myGrid.add(l1,j*2+1,i*2);
                }
                if(pos[i][j].isDown()){
                    Label l1=new Label("|");
                    l1.setTextAlignment(TextAlignment.CENTER);
                    myGrid.add(l1,j*2,i*2+1);
                }
                if(pos[i][j].getPlayer()==2)
                    b1.setStyle("-fx-background-color: #7417d1");
                else if(pos[i][j].getPlayer()==1)
                    b1.setStyle("-fx-background-color: #17d117");


                myGrid.add(b1, j*2, i*2);
            }
    }

    @FXML
    private void CreateButton(ActionEvent e) {
        if (Game.getGameBoard().getSize() > 0) {
            Game.getGameBoard().generateBoard(Game.getGameBoard().getSize());

            gameFinished=false;
            lastY=-1; lastX=-1;
            winner.setText("");
            myGrid.getChildren().clear();
            drawEdges();
            addFunctionalityButton();
        }
    }
    @FXML
    private void saveButton(){

        try {
            FileOutputStream file = new FileOutputStream("save.ser");
            ObjectOutputStream out= new ObjectOutputStream(file);

            out.writeObject(Game.getGameBoard());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void loadButton(){

        try {
            FileInputStream file =new FileInputStream("save.ser");
            ObjectInputStream in= new ObjectInputStream(file);
            gameFinished=false;
            winner.setText("");
            Game.setGameBoard((Board) in.readObject());
            //clears the grid and draws the one from save
            myGrid.getChildren().clear();
            drawEdges();
            addFunctionalityButton();

            in.close();
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void exportButton(){
        try {
            WritableImage img= Game.getSc().getRoot().snapshot(new SnapshotParameters(),null);
            File f=new File("D:/facultate/E2_java_scripts/GitHub/Lab6","image_export.png");
            ImageIO.write(SwingFXUtils.fromFXImage(img,null),"PNG",f);


        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
