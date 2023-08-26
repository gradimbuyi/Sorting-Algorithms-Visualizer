package Logic;

import Graphics.VisualizerPanel;

import javax.swing.SwingWorker;
import java.util.ArrayList;

public class MergeSort extends Algorithms {
    public MergeSort(ArrayList<Integer> list, int arraySize, VisualizerPanel visualizerPanel) {
        super(list, arraySize, visualizerPanel);
    }

    @Override
    public String printInfo(int type) {
        if(type == 0) return "O(n log(n))";
        if(type == 1) return "O(n)";

        return null;
    }

    private void recursive(int low_index, int high_index) throws InterruptedException {
        int middle;
        if(low_index < high_index) {
            middle = (low_index + high_index) / 2;

            recursive(low_index, middle);
            recursive(middle + 1, high_index);
            merge(low_index, middle, high_index);
        }
    }

    private void iterative() throws InterruptedException {
        int curr_size, low_index;

        for(curr_size = 1; curr_size <= getArraySize() - 1; curr_size = 2 * curr_size) {
            for(low_index = 0; low_index < getArraySize() - 1; low_index += 2 * curr_size) {
                int middle = Math.min(low_index + curr_size - 1, getArraySize() - 1);
                int high_index = Math.min(low_index + 2 * curr_size - 1, getArraySize() - 1);
                merge(low_index, middle, high_index);
            }
        }
    }

    protected void merge(int low_index, int middle, int high_index) throws InterruptedException {
        int left_length = middle - low_index + 1;
        int right_length = high_index - middle;

        Integer[] left = new Integer[left_length];
        Integer[] right = new Integer[right_length];

        for(int i = 0; i < left_length; i++) {
            left[i] = getList().get(low_index + i);
        }

        for(int i = 0 ; i < right_length; i++) {
            right[i] = getList().get(middle + i + 1);
        }

        int l = 0, r = 0;

        for(int i = low_index; i <= high_index; i++) {
            if((l < left_length) && (r >= right_length || (compare(right[r], left[l]) == 1))){
                getList().set(i, left[l]);
                Thread.sleep(10);
                getVisualizerPanel().repaint();
                l++;

            } else {
                getList().set(i, right[r]);
                Thread.sleep(10);
                getVisualizerPanel().repaint();
                r++;
            }
        }
    }

    @Override
    public void sortList() {
        SwingWorker<Void, Void> animate = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                recursive(0, getArraySize() - 1);
                //iterative();
                return null;
            }
        };

        getVisualizerPanel().setAnimate(animate);
        animate.execute();
    }
}
