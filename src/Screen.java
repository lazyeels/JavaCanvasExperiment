import java.awt.*;       // Using AWT's Graphics and Color
import javax.swing.*;    // Using Swing's components and containers

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class Screen extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH  = 640;
    public static final int CANVAS_HEIGHT = 480;
    public static final Color CANVAS_BACKGROUND = Color.BLACK;
    public Player player;
    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    public final DrawCanvas canvas;

    // Constructor to set up the GUI components and event handlers
    public Screen() {
        canvas = new DrawCanvas();    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvas);
        // or "setContentPane(canvas);"

        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("Game");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show

        player = new Player(canvas);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                switch(evt.getKeyCode()) {
                    case KeyEvent.VK_LEFT:  moveLeft();  break;
                    case KeyEvent.VK_RIGHT: moveRight(); break;
                    case KeyEvent.VK_UP:  moveUp();  break;
                    case KeyEvent.VK_DOWN: moveDown(); break;
                }
            }
        });
    }


    // Helper method to move the sprite left
    public void moveLeft() {
        // Save the current dimensions for repaint to erase the sprite
        int savedX = player.x;
        // update sprite
        player.moveLeft();
        // Repaint only the affected areas, not the entire JFrame, for efficiency
        canvas.repaint(savedX, player.y, player.width, player.height); // Clear old area to background
        canvas.repaint(player.x, player.y, player.width, player.height); // Paint new location
    }

    // Helper method to move the sprite right
    public void moveRight() {
        // Save the current dimensions for repaint to erase the sprite
        int savedX = player.x;
        player.moveRight();
        // Repaint only the affected areas, not the entire JFrame, for efficiency
        canvas.repaint(savedX, player.y, player.width, player.height); // Clear old area to background
        canvas.repaint(player.x, player.y, player.width, player.height); // Paint at new location
    }

    // Helper method to move the sprite left
    public void moveUp() {
        // Save the current dimensions for repaint to erase the sprite
        int savedY = player.y;
        player.moveUp();
        // Repaint only the affected areas, not the entire JFrame, for efficiency

        canvas.repaint(player.x, savedY, player.width, player.height); // Clear old area to background
        canvas.repaint(player.x, player.y, player.width, player.height); // Paint at new location
    }

    // Helper method to move the sprite right
    public void moveDown() {
        // Save the current dimensions for repaint to erase the sprite
        int savedY = player.y;

        player.moveDown();

        // Repaint only the affected areas, not the entire JFrame, for efficiency
        canvas.repaint(player.x, savedY, player.width, player.height); // Clear old area to background
        canvas.repaint(player.x, player.y, player.width, player.height); // Paint at new location
    }
    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */
    public class DrawCanvas extends JPanel {
        // Override paintComponent to perform your own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
            setBackground(Color.BLACK);  // set background color for this JPanel

            super.paintComponent(g);
            setBackground(CANVAS_BACKGROUND);

            player.paint(g);  // the sprite paints itself
        }
    }
    // The entry main method
    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Screen(); // Let the constructor do the job

            }
        });
    }
}