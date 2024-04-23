import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;    // Using Swing's components and containers

public class Player {
    private BufferedImage[] walkingLeft; // Gets the upper left images of my sprite sheet
    private BufferedImage[] walkingRight;
    private BufferedImage[] walkingUp;
    private BufferedImage[] walkingDown;
    private BufferedImage[] standing;

    // These are animation states
    public Animation animation;
    public Animation anim_walkLeft;
    public Animation anim_walkRight;
    public Animation anim_walkUp;
    public Animation anim_walkDown;
    public Animation anim_idle;

    public int x;
    public int y;

    public int width;
    public int height;

    public Player(Screen.DrawCanvas canvas)
    {
        this.x = 100;
        this.y = 100;
        this.width = 32;
        this.height = 32;

// Images for each animation
        this.walkingLeft = new BufferedImage[]{Sprite.getSprite(0, 1), Sprite.getSprite(1, 1), Sprite.getSprite(2, 1)}; // Gets the upper left images of my sprite sheet
        this.walkingRight = new BufferedImage[]{Sprite.getSprite(0, 2), Sprite.getSprite(1, 2), Sprite.getSprite(2, 2)};
        this.walkingUp = new BufferedImage[]{Sprite.getSprite(0, 3), Sprite.getSprite(1, 3), Sprite.getSprite(2, 3)};
        this.walkingDown = new BufferedImage[]{Sprite.getSprite(0, 0), Sprite.getSprite(1, 0), Sprite.getSprite(2, 0)};
        this.standing = new BufferedImage[]{Sprite.getSprite(1, 0)};

// These are animation states
        this.anim_walkLeft = new Animation(this.walkingLeft, 3);
        this.anim_walkRight = new Animation(this.walkingRight, 3);
        this.anim_walkUp = new Animation(this.walkingUp, 3);
        this.anim_walkDown = new Animation(this.walkingDown, 3);
        this.anim_idle = new Animation(this.standing, 3);

// This is the actual animation
        this.animation = anim_idle;
    }


    // Helper method to move the sprite left
    public void moveLeft() {
        // Save the current dimensions for repaint to erase the sprite
        int savedX = this.x;
        // update sprite
        this.x -= 10;
        this.animation = this.anim_walkLeft;
        this.animation.start();
        this.animation.update();
        //
        // Repaint only the affected areas, not the entire JFrame, for efficiency
        //canvas.repaint(savedX, player.y, player.width, player.height); // Clear old area to background
        //canvas.repaint(player.x, player.y, player.width, player.height); // Paint new location
    }

    // Helper method to move the sprite right
    public void moveRight() {
        // Save the current dimensions for repaint to erase the sprite
        int savedX = this.x;
        // update sprite
        this.x += 10;
        this.animation = this.anim_walkRight;
        this.animation.start();
        this.animation.update();
        //this.update();
        // Repaint only the affected areas, not the entire JFrame, for efficiency
        //canvas.repaint(savedX, player.y, player.width, player.height); // Clear old area to background
        //canvas.repaint(player.x, player.y, player.width, player.height); // Paint at new location
    }

    // Helper method to move the sprite left
    public void moveUp() {
        // Save the current dimensions for repaint to erase the sprite
        int savedY = this.y;
        // update sprite
        this.y -= 10;
        this.animation = this.anim_walkUp;
        this.animation.start();
        this.animation.update();
        //
        // Repaint only the affected areas, not the entire JFrame, for efficiency
        //canvas.repaint(player.x, savedY, this.width, this.height); // Clear old area to background
        //canvas.repaint(player.x, this.y, this.width, this.height); // Paint at new location
    }

    // Helper method to move the sprite right
    public void moveDown() {
        // Save the current dimensions for repaint to erase the sprite
        int savedY = this.y;
        // update sprite
        this.y += 10;
        this.animation = this.anim_walkDown;
        this.animation.start();
        this.animation.update();
        //
        // Repaint only the affected areas, not the entire JFrame, for efficiency
        //this.canvas.repaint(this.x, savedY, this.width, this.height); // Clear old area to background
        //this.canvas.repaint(this.x, this.y, this.width, this.height); // Paint at new location
    }

    public void update()
    {
        //this.animation.update();
    }
    public void paint(Graphics g) {
        //Image img = this.getSprite();
        //g.drawImage(img, this.x, this.y, null);
        g.drawImage(this.animation.getSprite(), x, y, null);
    }
}
