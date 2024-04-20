
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class Sprite {
    int _imageCount = 0;
    int _animationStart = 0;
    int _spriteSize;
    int _frameCount;
    public int x;
    public int y;
    public int width;
    public int height;

    BufferedImage[] sprites;

    public Sprite(String sheetName, int frameCount, int columns, int rows, int frameSize){

        _frameCount = frameCount;

        this.x = 50;
        this.y = 50;

        this.width = frameSize;
        this.height = frameSize;

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("C:/Users/mgikh/Desktop/JavaPractice/Canvas/src/img/" + sheetName + ".PNG"));
            buildSprites(spriteSheet, columns, rows, frameSize);
        } catch(IOException e) {
            System.out.println("Image not found!");
            e.printStackTrace();
        }
    }

    public void triggerUpdate() {
        System.out.println("image count is " + _imageCount);

        if (_imageCount < _frameCount-1) {
            _imageCount += 1;
        } else {
            _imageCount = 0;
        }
   }

   private void buildSprites(BufferedImage spriteSheet, int columns, int rows, int frameSize){
       sprites = new BufferedImage[25];
       for(int x = 0; x < columns; x++){
           for(int y = 0; y < rows; y++){
               sprites[(x * 5) + y] = spriteSheet.getSubimage(
                       x * (frameSize + 1),  // my sprite software adds 1 pixel border to each frame
                       y * (frameSize + 1),
                       frameSize,
                       frameSize
               );
           }
       }
   }

   public BufferedImage getSprite(int imageNumber){
        return sprites[imageNumber];
   }

   public BufferedImage getSprite(){
        return sprites[_imageCount];
   }

   public void paint(Graphics g) {
       Image img = this.getSprite(1);
       g.drawImage(img, this.x, this.y);
   }
}