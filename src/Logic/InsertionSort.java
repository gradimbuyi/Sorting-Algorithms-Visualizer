package Logic;

import Graphics.VisualizerPanel;
import javax.swing.SwingWorker;
import java.util.ArrayList;

public class InsertionSort extends Algorithms {

    public InsertionSort(ArrayList<Integer> list, int arraySize, VisualizerPanel visualizerPanel) {
        super(list, arraySize, visualizerPanel);
    }

    @Override
    public String printInfo(int type) {
        if(type == 0) return "O(n) or O(n^2)";
        if(type == 1) return "O(1)";
        return null;
    }

    private int insertion_sort_find_position(int high_index, int current) throws InterruptedException {
        int j = high_index;

        while(j >= 0 && compare(getList().get(j), current) == 1) {
            getList().set(j + 1, getList().get(j));
            Thread.sleep(50);
            getVisualizerPanel().repaint();
            j--;
        }

        return j + 1;
    }

    @Override
    public void sortList() {
        SwingWorker<Void, Void> animate = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int low_index = 0;
                int high_index;

                for(int i = 1; i < getArraySize(); i++) {
                    int current = getList().get(i);
                    high_index = i - 1;

                    low_index = insertion_sort_find_position(high_index, current);
                    getList().set(low_index, current);

                    Thread.sleep(50);
                    getVisualizerPanel().repaint();
                }

                return null;
            }
        };

        getVisualizerPanel().setAnimate(animate);
        animate.execute();
    }
}
