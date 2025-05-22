import java.awt.*;
import javax.swing.*;

public class ShooterEnemy extends EnemyBase {
    private static final int FIRE_RATE = 60; // 🔥 Shoots once every 60 frames (~1.2s)
    private static Image enemyImage;
    private int fireCooldown = 0; // Cooldown counter

    // Load image once
    static {
        enemyImage = new ImageIcon("shootingenemy.jpg").getImage();
    }

    public ShooterEnemy(int x, int y) {
        super(x, y);
    }

    public boolean shouldShoot() {
        if (fireCooldown > 0) {
            fireCooldown--; // Decrease cooldown
            return false;   // Can't shoot yet
        }
        fireCooldown = FIRE_RATE; // Reset cooldown
        return true;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(enemyImage, x, y, WIDTH, HEIGHT, null);
    }
}
