package Graphics;

import javax.swing.JFrame;

public class VisualizerFrame {
    private final JFrame frame;
    private final VisualizerPanel panel;

    public VisualizerFrame() {
        /* Initializes frame and panel */
        this.frame = initializeFrame();
        this.panel = new VisualizerPanel();

        /* Displays frame and panel */
        this.frame.add(panel);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    /* Default Frame settings */
    private JFrame initializeFrame() {
        JFrame frame = new JFrame();

        frame.setTitle("Sorting Algorithms Visualizer");
        frame.setLayout(null);
        frame.setSize(1580, 900);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);

        return frame;
    }
}
