package Logic;

import Graphics.VisualizerPanel;
import java.util.ArrayList;

public abstract class Algorithms {
    private final ArrayList<Integer> list;
    private final int arraySize;
    private final VisualizerPanel visualizerPanel;

    /* Default constructor for the algorithms */
    public Algorithms(ArrayList<Integer> list, int arraySize, VisualizerPanel visualizerPanel) {
        this.arraySize = arraySize;
        this.list = list;
        this.visualizerPanel = visualizerPanel;
    }

    /* Swap the location of 2 integers */
    public void swap(int locationOne, int locationTwo) throws InterruptedException {
        int temp = this.getList().get(locationOne);
        this.getList().set(locationOne, this.getList().get(locationTwo));
        this.getList().set(locationTwo, temp);

        Thread.sleep(100);
        visualizerPanel.repaint();
    }

    public int compare(int numOne, int numTwo) {
        if(numOne > numTwo) return 1;
        else if(numOne == numTwo) return 0;

        return -1;
    }

    /* Prints Algorithms time and space complexity */
    public abstract String printInfo(int type);

    /* Sort the list */
    public abstract void sortList();

    public ArrayList<Integer> getList() {
        return list;
    }

    public int getArraySize() {
        return arraySize;
    }

    public VisualizerPanel getVisualizerPanel() {
        return visualizerPanel;
    }
}
