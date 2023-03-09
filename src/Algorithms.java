import java.util.Random;

public abstract class Algorithms {
    private int [] list;
    private int arraySize;
    private Random random;

    /* Default constructor for each of our algorithms */
    public Algorithms(int arraySize) {
        this.arraySize = arraySize;
        this.random = new Random();
        this.list = generateNumbers();
    }

    /* Generates random integer values */
    private int [] generateNumbers() {
        list = new int[this.arraySize];

        for(int i = 0; i < this.arraySize; i++) {
            list[i] = random.nextInt(5, 25);
        }

        return list;
    }

    /* Swap the location of 2 integers */
    public void swap(int locationOne, int locationTwo) {
        int temp = this.getList()[locationOne];
        this.getList()[locationOne] = this.getList()[locationTwo];
        this.getList()[locationTwo] = temp;
    }

    /* Prints our list of integers */
    public void printList() {
        for(int i =  0; i < this.arraySize; i++) {
            System.out.print(this.list[i] + " ");
        }
        System.out.println();
    }

    /* Abstract method responsible for the logic of each sorting algorithm */
    public abstract void sortList();

    public int[] getList() {
        return list;
    }

    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }
}
