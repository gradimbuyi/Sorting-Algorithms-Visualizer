public class Main {

    public static void main(String[] args) {
        Algorithms bubbleSort = new BubbleSort(50);
        Algorithms selectionSort = new SelectionSort(50);

        System.out.println("Bubble Sort");
        bubbleSort.printList(); bubbleSort.sortList();

        System.out.println("Seleciton Sort");
        selectionSort.printList(); selectionSort.sortList();
    }


}