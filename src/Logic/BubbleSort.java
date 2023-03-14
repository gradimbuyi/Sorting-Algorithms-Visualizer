package Logic;

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
    public void sortList() {
        for(int i = 0; i < this.getArraySize() - 1; i++) {
            for(int j = 0; j < this.getArraySize() - i - 1; j++) {
                if(this.getList().get(j) > this.getList().get(j + 1)) swap(j, j + 1);
                printList();
            }
        }   System.out.println();
    }
}
