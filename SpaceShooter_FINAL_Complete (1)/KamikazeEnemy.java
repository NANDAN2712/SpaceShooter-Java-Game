import java.awt.*;
import javax.swing.*;

public class KamikazeEnemy extends EnemyBase {
    private static Image enemyImage;

    // Load image once
    static {
        enemyImage = new ImageIcon("image.png").getImage();
    }

    public KamikazeEnemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(enemyImage, x, y, WIDTH, HEIGHT, null);
    }
}
