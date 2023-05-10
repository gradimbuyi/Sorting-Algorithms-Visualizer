package Logic;

import Graphics.VisualizerPanel;
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

    private void merge_sort_merge(int low_index_one, int high_index_one, int low_index_two, int high_index_two) {
        // variables for for loop
        int l = 0, r = 0;

        // calculates the length of the left and right temp array
        int left_length = high_index_one - low_index_one + 1;
        int right_length = high_index_two - high_index_one;

        Integer left[] = new Integer[left_length];
        Integer right[] = new Integer[right_length];

        for(int i = 0; i < left_length; i++) {
            left[i] = getList().get(low_index_one + i);
        }

        for(int i = 0; i < right_length; i++) {
            right[i] = getList().get(high_index_one + 1 + i);
        }

        for(int i = low_index_one; i < high_index_two; i++) {
            if((l < left_length) && (r >= right_length || compare(right[r], left[l]) == 1)) {
                getList().set(i, left[l]); l++;
            } else {
                getList().set(i, right[r]); r++;
            }
        }
    }

    private void merge_sort_recursive(int low_index, int high_index) {
        int middle;

        if(low_index < high_index) {
            middle = (low_index + high_index) / 2;

            // creates sublists and merges them
            merge_sort_recursive(low_index, middle);
            merge_sort_recursive(middle + 1, high_index);
            merge_sort_merge(low_index, middle, middle + 1, high_index);
        }
    }

    @Override
    public void sortList() {
        merge_sort_recursive(0, getArraySize() - 1);
        getVisualizerPanel().repaint();
    }
}
