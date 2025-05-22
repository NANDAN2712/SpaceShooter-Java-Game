import java.awt.*;
import javax.swing.*;

public class PowerUp {
    private int x, y;
    private static final int SPEED = 2;
    private static final int WIDTH = 30, HEIGHT = 30;
    private static Image powerUpImage;

    // Load power-up image once
    static {
        powerUpImage = new ImageIcon("powerup.jpg").getImage();
    }

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y += SPEED;
    }

    public boolean moveOutOfBounds() {
        move();
        return y > 600;
    }

    public void draw(Graphics g) {
        g.drawImage(powerUpImage, x, y, WIDTH, HEIGHT, null);
    }

    public boolean collidesWith(Spaceship player) {
        return getBounds().intersects(player.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
