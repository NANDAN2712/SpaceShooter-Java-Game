import java.awt.*;

public class EnemyBullet {
    private int x, y;
    private static final int SPEED = 4;
    private static final int WIDTH = 5, HEIGHT = 10;

    public EnemyBullet(int x, int y) {
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
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public boolean collidesWith(Spaceship player) {
        return getBounds().intersects(player.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
