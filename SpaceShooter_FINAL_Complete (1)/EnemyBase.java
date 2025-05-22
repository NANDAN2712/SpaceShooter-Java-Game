import java.awt.*;

public abstract class EnemyBase {
    protected int x, y;
    protected static final int WIDTH = 50, HEIGHT = 50;
    protected static final int SPEED = 2;

    public EnemyBase(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y += SPEED;
    }

    public boolean moveOutOfBounds() {
        return y > 600;
    }

    public abstract void draw(Graphics g); // Force subclasses to implement drawing

    public int getX() { return x; }
    public int getY() { return y; }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
