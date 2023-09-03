package Logic;

import Graphics.VisualizerPanel;

import javax.swing.SwingWorker;
import java.util.ArrayList;

public class MergeInsertionSort extends Algorithms {
    private static MergeSort mergeSort;
    private static InsertionSort insertionSort;

    public MergeInsertionSort(ArrayList<Integer> list, int arraySize, VisualizerPanel visualizerPanel) {
        super(list, arraySize, visualizerPanel);
        mergeSort = new MergeSort(list, arraySize, visualizerPanel);
        insertionSort = new InsertionSort(list, arraySize, visualizerPanel);
    }

    @Override
    public String printInfo(int type) {
        return null;
    }

    private void merge_insertion(int low_index, int high_index) throws InterruptedException {
        int middle;

        if(low_index < high_index) {
            middle = (low_index + high_index) / 2;
            merge_insertion(low_index, middle);
            merge_insertion(middle + 1, high_index);

            if(high_index - low_index <= 25) insertionSort.insertion_internal();
            else mergeSort.merge(low_index, middle, high_index);
        }
    }

    @Override
    public void sortList() {
        SwingWorker<Void, Void> animate = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                merge_insertion(0, getArraySize() - 1);
                return null;
            }
        };

        getVisualizerPanel().setAnimate(animate);
        animate.execute();
    }
}
