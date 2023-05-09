package Logic;

import Graphics.VisualizerPanel;
import java.util.ArrayList;

public class MergeSort extends Algorithms {

    public MergeSort(ArrayList<Integer> list, int arraySize) {
        super(list, arraySize);
    }

    @Override
    public String printInfo(int type) {
        if(type == 0) return "O(n log(n))";
        if(type == 1) return "O(n)";

        return null;
    }

    @Override
    public void sortList(VisualizerPanel visualizerPanel) {

    }
}
