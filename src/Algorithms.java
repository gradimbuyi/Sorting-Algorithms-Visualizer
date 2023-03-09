import java.util.Random;

public abstract class Algorithms {
    private int [] list;
    private int arraySize;
    private Random random;

    public Algorithms(int arraySize) {
        this.arraySize = arraySize;
        this.random = new Random();
        this.list = generateNumbers();
    }

    private int [] generateNumbers() {
        list = new int[this.arraySize];

        for(int i = 0; i < this.arraySize; i++) {
            list[i] = random.nextInt(5, 25);
        }

        return list;
    }

    public void swap(int currentLocation) {
        int temp = this.list[currentLocation];
        this.list[currentLocation] = this.list[currentLocation + 1];
        this.list[currentLocation + 1] = temp;
    }

    public void printList() {
        for(int i =  0; i < this.arraySize; i++) {
            System.out.print(this.list[i] + " ");
        }
        System.out.println();
    }

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
