package sample;

import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    public static final int WIDTH = 1500;
    public static final int HEIGHT = 750;
    public static final int LENGTH = 9;
    public static final int SIZE = 30;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Group group = new Group();
        ComparableShape[] array = generateRndArray(LENGTH);
        for(int i = 0; i < array.length; i++){
            group.getChildren().add(array[i].getShape());
        }

        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.GREY);

        primaryStage.setTitle("CG_Task4fx");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE){
                SequentialTransition sq = new SequentialTransition();
                sq.getChildren().addAll(SortSamples.selectionSort(array));
                sq.play();
            }
        });
    }

    public static ComparableShape[] generateRndArray(int length){
        Random rnd = new Random();
        ComparableShape[] array = new ComparableShape[length];
        for(int i = 0; i < length; i++){
            int sizeType = rnd.nextInt(3) + 1;
            int shapeType = rnd.nextInt(3);
            int sizeNew = SIZE*sizeType;
            switch (shapeType){
                case 0:
                    array[i] = new ComparableShape(new Circle(sizeNew/2), 0, sizeType);
                    break;
                case 1:
                    array[i] = new ComparableShape(new Polygon(
                            0, -sizeNew/2,
                            sizeNew/2, sizeNew/2,
                            -sizeNew/2, sizeNew/2
                    ), 3, sizeType);
                    break;
                case 2:
                    array[i] = new ComparableShape(new Polygon(
                            -sizeNew/2, -sizeNew/2,
                            sizeNew/2, -sizeNew/2,
                            sizeNew/2, sizeNew/2,
                            -sizeNew/2, sizeNew/2
                    ), 4, sizeType);
                    break;
            }
            array[i].getShape().translateXProperty().set(WIDTH/(length+1)*(i+1));
            array[i].getShape().translateYProperty().set(HEIGHT/2);
            array[i].getShape().setFill(Color.WHITE);
        }
        return array;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
