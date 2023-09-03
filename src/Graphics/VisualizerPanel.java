package Graphics;

import Logic.Algorithms;
import Logic.BubbleSort;
import Logic.InsertionSort;
import Logic.MergeInsertionSort;
import Logic.MergeSort;
import Logic.QuickSort;
import Logic.SelectionSort;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class VisualizerPanel extends JPanel implements ActionListener {
    /* Numbers and Algorithm variables */
    private final ArrayList<Integer> LIST_OF_NUMBERS;
    private Algorithms algorithm;

    /* Button variables */
    private final JComboBox<String> DROP_DOWN_MENU;
    private final JButton START_BUTTON;
    private final JButton RESET_BUTTON;

    /* variable for animation */
    private SwingWorker<Void, Void> animate = null;
    private final Integer SIZE = 1578;

    /* CONSTRUCTOR */
    public VisualizerPanel() {
        /* Generates the integers used to perform the visualization */
        LIST_OF_NUMBERS = generateNumbers();

        /* Initialize the buttons */
        DROP_DOWN_MENU = initializeDropDownMenu();
        START_BUTTON = initializeButton("Start", 0);
        RESET_BUTTON = initializeButton("Reset", 100);

        /* Add button and timer to Panel */
        this.add(DROP_DOWN_MENU);
        this.add(START_BUTTON);
        this.add(RESET_BUTTON);

        /* Initialize panel */
        this.setBackground(Color.black);
        this.setSize(1600, 872);
        this.setLayout(null);
    }

    /* Setter for SwingWorker */
    public void setAnimate(SwingWorker<Void, Void> animate) {
        this.animate = animate;
    }

    /* Method to initialize drop down menu */
    private JComboBox<String> initializeDropDownMenu() {
        JComboBox<String> menu = new JComboBox<>();

        menu.setSize(200, 25);
        menu.setLocation(1340, 100);

        /* Names of Algorithms */
        menu.addItem("Bubble Sort");
        menu.addItem("Selection Sort");
        menu.addItem("Insertion Sort");
        menu.addItem("Quick Sort");
        menu.addItem("Merge Sort");
        //menu.addItem("Merge-Insertion Sort");
        menu.addActionListener(this);

        return menu;
    }

    /* Method to initialize buttons */
    private JButton initializeButton(String name, int x) {
        JButton button = new JButton();

        button.setText(name);
        button.setSize(100, 25);
        button.setLocation(1339 + x,125);
        button.addActionListener(this);

        return button;
    }

    /* Generates random integer values */
    private ArrayList<Integer> generateNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();

        /* Generates numbers from 4 to 67 and shuffles our array */
        for(int i = 0; i < SIZE; i++) {
            numbers.add((int)((i / 9.5) + 2));
        }

        Collections.shuffle(numbers);
        return numbers;
    }

    /* Visualizes the data on the panel */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        /* Draws title */
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.BOLD, 30));
        graphics.drawString("Sorting Algorithms Visualizer", 600, 70);

        /* Draws rectangles representing each number in the list */
        paintInfo(graphics);
        paintRect(graphics);
    }

    /* Initialize algorithm for animation */
    private Algorithms initializeAlgorithm(String name) {
        Algorithms newAlgorithm = null;

        if(name.equals("Bubble Sort")) newAlgorithm = new BubbleSort(LIST_OF_NUMBERS, SIZE, this);
        if(name.equals("Selection Sort")) newAlgorithm = new SelectionSort(LIST_OF_NUMBERS, SIZE, this);
        if(name.equals("Insertion Sort")) newAlgorithm = new InsertionSort(LIST_OF_NUMBERS, SIZE, this);
        if(name.equals("Quick Sort")) newAlgorithm = new QuickSort(LIST_OF_NUMBERS, SIZE, this);
        if(name.equals("Merge Sort")) newAlgorithm = new MergeSort(LIST_OF_NUMBERS, SIZE, this);
        //if(name.equals("Merge-Insertion Sort")) newAlgorithm = new MergeInsertionSort(LIST_OF_NUMBERS, SIZE, this);

        return newAlgorithm;
    }

    /* Prints information related to the algorithm */
    private void paintInfo(Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.PLAIN, 15));
        graphics.drawString("Algorithm: " + DROP_DOWN_MENU.getSelectedItem(), 50, 120);

        /* Initialize algorithm */
        algorithm = initializeAlgorithm((String) Objects.requireNonNull(DROP_DOWN_MENU.getSelectedItem()));

        graphics.drawString("Time Complexity: " + algorithm.printInfo(0), 50, 150);
        graphics.drawString("Space Complexity: " + algorithm.printInfo(1), 50, 180);
    }

    /* Draws rectangle based on values in the list */
    private void paintRect(Graphics graphics) {
        int x = 5;
        int height;

        for (int i = 0; i < SIZE; i++) {
            height = LIST_OF_NUMBERS.get(i) * 4;
            graphics.fillRect(x, 865 - height, 1, height);

            x = x + 1;
        }
    }

    private boolean isSorted() {
        for(int i = 0; i < SIZE; i++) {
            if(LIST_OF_NUMBERS.get(i) != i + 4) return false;
        }
        return true;
    }

    /* Animates our program */
    @Override
    public void actionPerformed(ActionEvent event) {
        boolean isRunning = false;

        if(animate != null) {
            animate.cancel(true);
        }

        if(event.getSource() == DROP_DOWN_MENU || event.getSource() == RESET_BUTTON) {
            animate = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for(int i = 0; i < 20; i++) {
                        Collections.shuffle(LIST_OF_NUMBERS);
                        Thread.sleep(10); repaint();
                    }
                    return null;
                }
            };

            animate.execute();

        } else if(event.getSource() == START_BUTTON) {
            //if(!isRunning && !isSorted()) {
                //START_BUTTON.setText("Pause");
                //repaint();
                //algorithm.sortList();
            //}

            repaint();
            algorithm.sortList();
        }
    }
}