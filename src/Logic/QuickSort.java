package Logic;

import Graphics.VisualizerPanel;

import javax.swing.SwingWorker;
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

    private int repartition(VisualizerPanel visualizerPanel, int low_index, int high_index) throws InterruptedException {
        int i = low_index;

        for(int j = low_index; j < high_index; j++) {
            if(compare(getList().get(j), getList().get(high_index)) == -1) {
                swap(i, j); i++;

                Thread.sleep(getSleep());
                visualizerPanel.repaint();
            }
        }

        swap(i, high_index);
        visualizerPanel.repaint();

        return i;
    }

    private void quick_sort_recursive(VisualizerPanel visualizerPanel, int low_index, int high_index) {

        SwingWorker<Void, Void> animate = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int pivot_index = repartition(visualizerPanel, low_index, high_index);
                if(pivot_index - 1 > low_index) quick_sort_recursive(visualizerPanel, low_index, pivot_index - 1);
                if(high_index > pivot_index) quick_sort_recursive(visualizerPanel,pivot_index + 1, high_index);

                return null;
            }
        };

        visualizerPanel.setAnimate(animate);
        animate.execute();
    }

    @Override
    public void sortList(VisualizerPanel visualizerPanel) {
        quick_sort_recursive(visualizerPanel,0, getArraySize() - 1);
    }
}
