package Tools;

import DAO.DaoCities;
import Data.EarthPosition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;
import java.util.List;

public class ScreenMap extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        Canvas c = new Canvas(1600, 800);
        DaoCities dc = new DaoCities();
        List<EarthPosition> list = dc.getLocations();

        List<Double> xCoord = new LinkedList<>();
        List<Double> yCoord = new LinkedList<>();

        list.stream().forEach(earthPosition -> {
            xCoord.add(earthPosition.xAxisProjection());
            yCoord.add(earthPosition.yAxisProjection());
        });

        double xmin= Collections.min(xCoord);
        double ymin=Collections.min(yCoord);
        System.out.println("Minim: " + xmin + " " + ymin);

        xCoord.stream().forEach(value -> { value -= xmin; });
        yCoord.stream().forEach(value ->{ value-=ymin;});

        double xmax= Collections.max(xCoord);
        double ymax= Collections.max(yCoord);

        System.out.println("Minim: " + xmax + " " + ymax);

        double globalRatio= Math.min(1600/xmax,800/ymax);


        for (int i=0; i< xCoord.size();i++ ) {
            c.getGraphicsContext2D().fillOval(xCoord.get(i) * globalRatio,
                    yCoord.get(i) * globalRatio, 4, 4);
            System.out.println(xCoord.get(i) * globalRatio + " " + yCoord.get(i) * globalRatio);

        }

        Group root = new Group();
        root.getChildren().add(c);

        stage.setTitle("Map");
        stage.setScene(new Scene(root, 1800, 900));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
