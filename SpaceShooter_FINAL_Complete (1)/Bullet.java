import java.awt.*;

public class Bullet {
    private int x, y;
    private static final int SPEED = 7;
    private static final int WIDTH = 5, HEIGHT = 10;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= SPEED;
    }

    public boolean moveOutOfBounds() {
        move();
        return y < 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public boolean collidesWith(EnemyBase enemy) {
        return getBounds().intersects(enemy.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
