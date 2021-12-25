package sample;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static sample.Main.LENGTH;
import static sample.Main.WIDTH;


public class SortSamples {

    public static List<Transition> selectionSort(ComparableShape[] array){
        List<Transition> transitions = new ArrayList<>();
        int iMin;

        for(int i = 0; i < array.length - 1; i++){
            iMin = i;
            transitions.add(colorCNode(array, Color.GREEN, iMin));

            for(int j = i + 1; j < array.length; j++){
                transitions.add(colorCNode(array, Color.RED, j));

                if(array[iMin].exceed(array[j]) == 1){
                    if(iMin == i){
                        transitions.add(colorCNode(array, iMin, j, Color.BLUE, Color.GREEN));
                    } else {
                        transitions.add(colorCNode(array, iMin, j, Color.WHITE, Color.GREEN));
                    }
                    iMin = j;
                } else {
                    transitions.add(colorCNode(array, Color.WHITE, j));
                }
            }

            if(iMin != i){
                transitions.add(swap(array, i, iMin));
            }
            transitions.add(colorCNode(array, Color.WHITE, i, iMin));
        }
        transitions.add(colorCNode(array, Color.GOLD));

        return transitions;
    }

    private static ParallelTransition swap(ComparableShape[] arr, int i, int j) {
        ParallelTransition pt = new ParallelTransition();
        int dxFactor = j - i;
        pt.getChildren().addAll(arr[i].moveX(WIDTH/(LENGTH+1)*dxFactor), arr[j].moveX(-WIDTH/(LENGTH+1)*dxFactor));

        ComparableShape tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

        return pt;
    }

    private static ParallelTransition colorCNode(ComparableShape[] arr, int a, int b, Color colorA, Color colorB) {
        ParallelTransition pt = new ParallelTransition();

        pt.getChildren().addAll(colorCNode(arr, colorA, a), colorCNode(arr, colorB, b));

        return pt;
    }

    private static ParallelTransition colorCNode(ComparableShape[] arr, Color color, int...a) {
        ParallelTransition pt = new ParallelTransition();

        for (int i = 0; i < a.length; i++) {
            FillTransition ft = new FillTransition();
            ft.setShape(arr[a[i]].getShape());
            ft.setToValue(color);
            ft.setDuration(Duration.millis(400));
            pt.getChildren().add(ft);
        }
        return pt;
    }

    private static ParallelTransition colorCNode(ComparableShape[] arr, Color color) {
        ParallelTransition pt = new ParallelTransition();
        List<ComparableShape> list = Arrays.asList(arr);

        for (ComparableShape c : list) {
            FillTransition ft = new FillTransition();
            ft.setShape(c.getShape());
            ft.setToValue(color);
            ft.setDuration(Duration.millis(400));
            pt.getChildren().add(ft);
        }
        return pt;
    }
}
