package Logic;

import Graphics.VisualizerPanel;
import javax.swing.SwingWorker;
import java.util.ArrayList;

public class SelectionSort extends Algorithms {
    public SelectionSort(ArrayList<Integer> list, int arraySize) {
        super(list, arraySize);
    }

    @Override
    public String printInfo(int type) {

        if(type == 0) return "O(n^2)";
        if(type == 1) return "O(1)";

        return null;
    }

    /* Method to find the lowest value in our array */
    private int findLowestValue(int currentLocation) {
        int lowestValueLocation = currentLocation;

        /* Loop through our array from our current location to the last index */
        for(int i = currentLocation + 1; i < getArraySize(); i++) {
            if(getList().get(lowestValueLocation) > getList().get(i)) {
                lowestValueLocation = i;
            }
        }

        /* Returns the location of our lowest value */
        return lowestValueLocation;
    }

    @Override
    public void sortList(VisualizerPanel visualizerPanel) {
        SwingWorker<Void, Void> animate = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int lowestValueLocation;

                for (int i = 0; i < getArraySize(); i++) {
                    lowestValueLocation = findLowestValue(i);
                    swap(lowestValueLocation, i);

                    Thread.sleep(5);
                    visualizerPanel.repaint();
                }

                return null;
            }
        };

        visualizerPanel.setAnimate(animate);
        animate.execute();
    }
}
