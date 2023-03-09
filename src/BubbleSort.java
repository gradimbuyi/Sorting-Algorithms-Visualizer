public class BubbleSort extends Algorithms {
    public BubbleSort(int arraySize) {
        super(arraySize);
    }

    @Override
    public void sortList() {
        for(int i = 0; i < this.getArraySize() - 1; i++) {
            for(int j = 0; j < this.getArraySize() - i - 1; j++) {
                if(this.getList()[j] > this.getList()[j + 1]) swap(j, j + 1);
                printList();
            }
        }   System.out.println();
    }
}
