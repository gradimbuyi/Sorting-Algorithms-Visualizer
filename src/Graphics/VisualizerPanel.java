package Graphics;
import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class VisualizerPanel extends JPanel implements ActionListener {

    /* Numbers and Algorithm variables */
    private ArrayList<Integer> list;
    private Algorithms algorithm;

    /* Timer variable for animation */
    private final Timer timer;

    /* Button variables */
    private final JComboBox<String> dropDownMenu;
    private final JButton start;
    private final JButton reset;

    /* CONSTRUCTOR */
    public VisualizerPanel() {

        /* Generates the integers used to perform the visualization */
        this.list = generateNumbers();

        /* Initialize the buttons */
        this.dropDownMenu = initializeDropDownMenu();
        this.start = initializeButton("Start", 0);
        this.reset = initializeButton("Reset", 100);

        /* Initialize the timer */
        this.timer = new Timer(10, this);
        this.timer.start();

        /* Add button and timer to Panel */
        this.add(dropDownMenu);
        this.add(start);
        this.add(reset);

        /* Initialize panel */
        this.setBackground(Color.black);
        this.setSize(1600, 872);
        this.setLayout(null);
    }

    /* Method to initialize drop down menu */
    private JComboBox<String> initializeDropDownMenu() {
        JComboBox<String> menu = new JComboBox<>();

        menu.setSize(200, 25);
        menu.setLocation(1340, 100);

        /* Names of Algorithms */
        menu.addItem("Bubble Sort");
        menu.addItem("Selection Sort");
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
        for(int i = 0; i < 63; i++) numbers.add(i + 4);
        Collections.shuffle(numbers); return numbers;
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
        paintInfo(graphics); paintRect(graphics);
    }

    /* Initialize algorithm for animation */
    private Algorithms initializeAlgorithm(String name) {
        Algorithms newAlgorithm = null;

        if(name.equals("Bubble Sort")) newAlgorithm = new BubbleSort(this.list, 63);
        if(name.equals("Selection Sort")) newAlgorithm = new SelectionSort(this.list, 63);

        return newAlgorithm;
    }

    /* Prints information related to the algorithm */
    private void paintInfo(Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.PLAIN, 15));
        graphics.drawString("Algorithm: " + this.dropDownMenu.getSelectedItem(), 50, 120);

        /* Initialize algorithm */
        this.algorithm = initializeAlgorithm((String) this.dropDownMenu.getSelectedItem());

        graphics.drawString("Time Complexity: " + algorithm.printInfo(0), 50, 150);
        graphics.drawString("Space Complexity: " + algorithm.printInfo(1), 50, 180);
    }

    /* Draws rectangle based on values in the list */
    private void paintRect(Graphics graphics) {
        int x = 5;
        int height;

        for (int i = 0; i < 63; i++) {
            height = list.get(i) * 10;
            graphics.fillRect(x, 865 - height, 20, height);

            x = x + 25;
        }
    }

    /* Animates our program */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == this.dropDownMenu) {
            Collections.shuffle(this.list); repaint();

        } else if(event.getSource() == this.reset) {
            Collections.shuffle(this.list); repaint();

        } else if(event.getSource() == this.start) {
            algorithm.sortList(); repaint();
        }
    }
}
