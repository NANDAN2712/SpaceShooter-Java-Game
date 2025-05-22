// Spaceship.java (Updated)
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Spaceship {
    private int x, y;
    private boolean movingLeft, movingRight, movingUp, movingDown;
    private static final int WIDTH = 50, HEIGHT = 50;
      private Image spaceshipImage;

    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
        loadImage(); // Load the image
    }

    private void loadImage() {
        try {
            spaceshipImage = ImageIO.read(new File("spaceship2.png")); // Load from project folder
        } catch (IOException e) {
            System.out.println("🚨 Error: Spaceship image not found!");
            spaceshipImage = null;
        }
    }

    public void draw(Graphics g) {
        if (spaceshipImage != null) {
            g.drawImage(spaceshipImage, x, y, WIDTH, HEIGHT, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }
   
    
    public void move() {
        if (movingLeft) x -= 5;
        if (movingRight) x += 5;
        if (movingUp) y -= 5;
        if (movingDown) y += 5;

        // Ensure spaceship stays within bounds
        if (x < 0) x = 0;
        if (x > 750) x = 750;
        if (y < 0) y = 0;
        if (y > 550) y = 550;
    }

    public void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            movingLeft = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            movingRight = true;
        } else if (keyCode == KeyEvent.VK_UP) {
            movingUp = true;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            movingDown = true;
        }
    }

    public void handleKeyRelease(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            movingLeft = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            movingRight = false;
        } else if (keyCode == KeyEvent.VK_UP) {
            movingUp = false;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            movingDown = false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {  
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void setPosition(int i, int j) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
    }
}
