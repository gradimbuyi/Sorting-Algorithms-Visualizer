package Graphics;

import Logic.*;

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

public class VisualizerPanel extends JPanel implements ActionListener {

    /* Numbers and Algorithm variables */
    private ArrayList<Integer> list;
    private Algorithms algorithm;

    /* Button variables */
    private final JComboBox<String> dropDownMenu;
    private final JButton start;
    private final JButton reset;

    /* variable for animation */
    private SwingWorker<Void, Void> animate = null;
    private final int size = 63;

    private int green = -1;
    private int red = -1;
    private int blue = -1;

    /* CONSTRUCTOR */
    public VisualizerPanel() {

        /* Generates the integers used to perform the visualization */
        list = generateNumbers();

        /* Initialize the buttons */
        dropDownMenu = initializeDropDownMenu();
        start = initializeButton("Start", 0);
        reset = initializeButton("Reset", 100);

        /* Add button and timer to Panel */
        this.add(dropDownMenu);
        this.add(start);
        this.add(reset);

        /* Initialize panel */
        this.setBackground(Color.black);
        this.setSize(1600, 872);
        this.setLayout(null);
    }

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
        menu.addItem("Merge Insertion Sort");

        menu.addActionListener(this);

        return menu;
    }

    /* Method to initalize buttons */
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
        for(int i = 0; i < size; i++) {
            numbers.add(i + 4);
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
        paintRect(graphics, green, red, blue);
    }

    /* Initialize algorithm for animation */
    private Algorithms initializeAlgorithm(String name) {
        Algorithms newAlgorithm = null;

        if(name.equals("Bubble Sort")) newAlgorithm = new BubbleSort(list, size);
        if(name.equals("Selection Sort")) newAlgorithm = new SelectionSort(list, size);
        if(name.equals("Insertion Sort")) newAlgorithm = new InsertionSort(list, size);
        if(name.equals("Quick Sort")) newAlgorithm = new QuickSort(list, size);
        if(name.equals("Merge Sort")) newAlgorithm = new MergeSort(list, size);
        if(name.equals("Merge Insertion Sort")) newAlgorithm = new MergeInsertionSort(list, size);

        return newAlgorithm;
    }

    /* Prints information related to the algorithm */
    private void paintInfo(Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.PLAIN, 15));
        graphics.drawString("Algorithm: " + dropDownMenu.getSelectedItem(), 50, 120);

        /* Initialize algorithm */
        algorithm = initializeAlgorithm((String) dropDownMenu.getSelectedItem());

        graphics.drawString("Time Complexity: " + algorithm.printInfo(0), 50, 150);
        graphics.drawString("Space Complexity: " + algorithm.printInfo(1), 50, 180);
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    private void resetColor() {
        this.green = -1; this.red = -1; this.blue = -1;
    }

    /* Draws rectangle based on values in the list */
    private void paintRect(Graphics graphics, int green, int red, int blue) {
        int x = 5;
        int height;

        for (int i = 0; i < size; i++) {

            if(green == i) graphics.setColor(Color.GREEN);
            else if(red == i) graphics.setColor(Color.RED);
            else if(blue == i) graphics.setColor(Color.CYAN);
            else graphics.setColor(Color.WHITE);

            height = list.get(i) * 10;
            graphics.fillRect(x, 865 - height, 20, height);

            x = x + 25;
        }
    }

    /* Animates our program */
    @Override
    public void actionPerformed(ActionEvent event) {

        if(animate != null) animate.cancel(true);

        this.resetColor();

        if(event.getSource() == dropDownMenu || event.getSource() == reset) {

            animate = new SwingWorker<>() {

                @Override
                protected Void doInBackground() throws Exception {
                    for(int i = 0; i < 20; i++) {
                        Collections.shuffle(list);
                        Thread.sleep(10);

                        repaint();
                    }
                    return null;
                }
            };

            animate.execute();
        }

        else if(event.getSource() == start) {
            algorithm.sortList(this);
        }

    }
}