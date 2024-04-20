import java.awt.*;       // Using AWT's Graphics and Color
import javax.swing.*;    // Using Swing's components and containers
import java.awt.event.*; // Using AWT's event classes and listener interfaces

/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class Screen extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH  = 640;
    public static final int CANVAS_HEIGHT = 480;
    public static final Color CANVAS_BACKGROUND = Color.BLACK;

    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private final DrawCanvas canvas;
    public Sprite sprite;     // the moving object

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
        setTitle("......");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show

        // "super" JFrame fires KeyEvent
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                switch(evt.getKeyCode()) {
                    case KeyEvent.VK_LEFT:  moveLeft();  break;
                    case KeyEvent.VK_RIGHT: moveRight(); break;
                }
            }
        });


        sprite = new Sprite("NPC",3, 3, 4, 26);
        sprite.x = 100;
        sprite.y = 100;
    }

    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */
    private class DrawCanvas extends JPanel {
        // Override paintComponent to perform your own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
            setBackground(Color.BLACK);  // set background color for this JPanel

            super.paintComponent(g);
            setBackground(CANVAS_BACKGROUND);
            sprite.paint(g);  // the sprite paints itself
        }
    }
    // Helper method to move the sprite left
    private void moveLeft() {
        // Save the current dimensions for repaint to erase the sprite
        int savedX = sprite.x;
        // update sprite
        sprite.x -= 10;
        // Repaint only the affected areas, not the entire JFrame, for efficiency
        canvas.repaint(savedX, sprite.y, sprite.width, sprite.height); // Clear old area to background
        canvas.repaint(sprite.x, sprite.y, sprite.width, sprite.height); // Paint new location
    }

    // Helper method to move the sprite right
    private void moveRight() {
        // Save the current dimensions for repaint to erase the sprite
        int savedX = sprite.x;
        // update sprite
        sprite.x += 10;
        // Repaint only the affected areas, not the entire JFrame, for efficiency
        canvas.repaint(savedX, sprite.y, sprite.width, sprite.height); // Clear old area to background
        canvas.repaint(sprite.x, sprite.y, sprite.width, sprite.height); // Paint at new location
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