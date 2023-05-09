package Logic;

import Graphics.VisualizerPanel;
import java.util.ArrayList;

public class QuickSort extends Algorithms {

    public QuickSort(ArrayList<Integer> list, int arraySize) {
        super(list, arraySize);
    }

    @Override
    public String printInfo(int type) {
        if(type == 0) return "O(n log(n)) or O(n^2)";
        if(type == 1) return "O(log(n))";

        return null;
    }

    @Override
    public void sortList(VisualizerPanel visualizerPanel) {

    }
}
