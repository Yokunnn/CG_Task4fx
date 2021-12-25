package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class ComparableShape {
    private Shape shape;
    private int vertexes;
    private int sizeType;

    public ComparableShape(Shape shape,int edges, int sizeType){
        this.shape = shape;
        this.vertexes = edges;
        this.sizeType = sizeType;
    }

    public Shape getShape() {
        return shape;
    }
    public int getEdges() {
        return vertexes;
    }
    public int getSizeType() {
        return sizeType;
    }

    public TranslateTransition moveX(int x) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this.shape);
        t.setDuration(Duration.millis(400));
        t.setByX(x);

        return t;
    }

    public int exceed(ComparableShape shape){
        if(this.vertexes > shape.vertexes){
            return 1;
        } else if(this.vertexes < shape.vertexes){
            return -1;
        } else if(this.sizeType > shape.sizeType){
            return 1;
        } else if(this.sizeType < shape.sizeType){
            return -1;
        }
        return 0;
    }
}
