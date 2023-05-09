package Logic;

import Graphics.VisualizerPanel;

import javax.swing.SwingWorker;
import java.awt.*;
import java.util.ArrayList;

public class BubbleSort extends Algorithms {
    public BubbleSort(ArrayList<Integer> list, int arraySize) {
        super(list, arraySize);
    }

    @Override
    public String printInfo(int type) {
        if(type == 0) return "O(n+k)";
        if(type == 1) return "O(1)";

        return null;
    }

    @Override
    public void sortList(VisualizerPanel visualizerPanel) {
        SwingWorker<Void, Void> animate = new SwingWorker<>() {

            @Override
            protected Void doInBackground() throws Exception {

                for(int i = 0; i < getArraySize() - 1; i++) {
                    for(int j = 0; j < getArraySize() - i - 1; j++) {

                        if(getList().get(j) > getList().get(j + 1)) {
                            swap(j, j + 1);

                            Thread.sleep(getSleep() - 90);

                            visualizerPanel.setRed(j + 2);
                            visualizerPanel.repaint();
                        }
                    }
                }

                return null;
            }
        };

        visualizerPanel.setRed(-1);

        visualizerPanel.setAnimate(animate);
        animate.execute();
    }
}
