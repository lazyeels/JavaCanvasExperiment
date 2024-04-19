import java.awt.*;
import javax.swing.JFrame;

public class Screen extends Draw
{
   public JFrame frame;
   public Canvas canvas;
   public int screenHeight;
   public int screenWidth;

   public Screen()
   {
       this.frame = new JFrame("My Drawing");
       this.canvas = new Draw();

   }
   public void Init()
   {
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       screenSize.getHeight();
       screenSize.getWidth();

       this.screenHeight = screenSize.height;
       this.screenWidth = screenSize.width;

       this.canvas.setSize(this.screenWidth, this.screenHeight);

       this.frame.setBounds(0,0,this.screenWidth,this.screenHeight);

       this.frame.add(this.canvas);
       this.frame.pack();

       this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

       this.frame.setVisible(true);

   }
   @Override
   public void paint(Graphics g) {
       g.fillOval(100, 100, 500, 500);
   }

   public static void main(String[] args) {
      Screen s = new Screen();
      s.Init();


   }

}