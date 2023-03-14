package Logic;

import java.util.ArrayList;

public abstract class Algorithms {
    private ArrayList<Integer> list;
    private int arraySize;

    /* Default constructor for the algorithms */
    public Algorithms(ArrayList<Integer> list, int arraySize) {
        this.arraySize = arraySize;
        this.list = list;
    }

    /* Swap the location of 2 integers */
    public void swap(int locationOne, int locationTwo) {
        int temp = this.getList().get(locationOne);
        this.getList().set(locationOne, this.getList().get(locationTwo));
        this.getList().set(locationTwo, temp);
    }

    /* Prints list of integers */
    public void printList() {
        for(int i =  0; i < this.arraySize; i++) {
            System.out.print(this.list.get(i) + " ");
        }
        System.out.println();
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
}
