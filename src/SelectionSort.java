public class SelectionSort extends Algorithms {
    public SelectionSort(int arraySize) {
        super(arraySize);
    }

    /* Method to find the lowest value in our array */
    private int findLowestValue(int currentLocation) {
        int lowestValueLocation = currentLocation;

        /* Loop through our array from our current location to the last index */
        for(int i = currentLocation + 1; i < getArraySize(); i++) {
            if(getList()[lowestValueLocation] > getList()[i]) {
                lowestValueLocation = i;
            }
        }

        /* Returns the location of our lowest value */
        return lowestValueLocation;
    }

    @Override
    public void sortList() {
        int lowestValueLocation;

        for(int i = 0; i < getArraySize(); i++) {
            lowestValueLocation = findLowestValue(i);
            swap(lowestValueLocation, i);
            printList();
        }

        System.out.println();
    }
}
