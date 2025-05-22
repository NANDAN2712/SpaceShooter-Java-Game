import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

public class SpaceShooter extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Spaceship player;
    private ArrayList<Bullet> bullets;
    private ArrayList<EnemyBase> enemies;
    private ArrayList<EnemyBullet> enemyBullets;
    private ArrayList<PowerUp> powerUps;

    private int score = 0;
    private int health = 5;
    private boolean paused = false;
    private boolean gameStarted = false; // Track whether the game has started

    private JButton resumeButton, restartButton, startButton; // Add a start button
    private Image backgroundImage;
    private JPanel startPanel;

    public SpaceShooter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
setPreferredSize(screenSize);

        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        addKeyListener(this);

        // Load background image
        backgroundImage = new ImageIcon("image1.png").getImage();

        // Initialize game objects
        initializeGame();

        // Timer to update game state
        timer = new Timer(20, this);
        timer.start();

        // Initialize buttons and start screen
        setupButtons();
        setupStartScreen();
    }

    private void setupButtons() {
        resumeButton = new JButton("Resume");
        restartButton = new JButton("Restart");

        resumeButton.setBounds(320, 300, 150, 40);
        restartButton.setBounds(320, 350, 150, 40);

        resumeButton.setFocusable(false);
        restartButton.setFocusable(false);

        resumeButton.addActionListener(e -> togglePause());
        restartButton.addActionListener(e -> restartGame());

        setLayout(null);
        add(resumeButton);
        add(restartButton);

        resumeButton.setVisible(false);
        restartButton.setVisible(false);
    }

    // Set up the Start Menu
    private void setupStartScreen() {
        startPanel = new JPanel();
        startPanel.setLayout(null);
        startPanel.setBackground(Color.BLACK);  // Black background for the start screen

        startButton = new JButton("Start Game");
        startButton.setBounds(320, 250, 150, 40);  // Button placement
        startButton.setFocusable(false);
        startButton.addActionListener(e -> startGame());

        startPanel.add(startButton);  // Add the start button to the start screen panel
        startPanel.setPreferredSize(new Dimension(800, 600));  // Size of the panel

        setLayout(new BorderLayout());  // Use BorderLayout for adding panels
        add(startPanel, BorderLayout.CENTER);  // Add the start screen to the frame initially
    }

    // Transition to the main game
    private void startGame() {
        gameStarted = true;
        remove(startPanel);  // Remove the start screen
        initializeGame(); // Initialize game objects
        setFocusable(true); // Allow key press
        repaint();
    }

    // Initialize game objects
    private void initializeGame() {
        player = new Spaceship(375, 500);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        powerUps = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameStarted) {
            drawStartScreen(g);
            return;
        }

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        if (health > 0 && !paused) {
            player.draw(g);
            for (Bullet bullet : bullets) bullet.draw(g);
            for (EnemyBase enemy : enemies) enemy.draw(g);
            for (EnemyBullet bullet : enemyBullets) bullet.draw(g);
            for (PowerUp powerUp : powerUps) powerUp.draw(g);

            // Draw score
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 40);

            // Draw health (Hearts)
            g.setColor(Color.RED);
            int healthX = 10, healthY = 20;
            for (int i = 0; i < health; i++) {
                g.drawString("❤️", healthX + (i * 30), healthY);
            }
            g.setColor(Color.cyan);
g.setFont(new Font("Arial", Font.ITALIC, 16));
g.drawString("Developed by Nandan & Arush", getWidth() - 260, getHeight() - 20);

        }

        // Draw Game Over screen
        if (health <= 0) {
            gameOverScreen(g);
        }

        // Draw Pause Screen
        if (paused) {
            drawPauseScreen(g);
        }
    }

    private void drawStartScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Space Shooter", 270, 100);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press 'Start Game' to Begin", 270, 200);
    }

    private void drawPauseScreen(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black overlay
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("PAUSED", 320, 200);
    }

    private void gameOverScreen(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("GAME OVER", 250, 240);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Your score: " + score, 280, 280);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press ENTER to Restart", 290, 320);
        g.setFont(new Font("Arial", Font.ITALIC, 18));
g.setColor(Color.CYAN);
g.drawString("Developed by Arshiva , Arush , Harshita, Nandan , Tanushree", 160, 360);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (paused || health <= 0 || !gameStarted) return;

        player.move();
        bullets.removeIf(Bullet::moveOutOfBounds);
        enemies.removeIf(EnemyBase::moveOutOfBounds);
        enemyBullets.removeIf(EnemyBullet::moveOutOfBounds);
        powerUps.removeIf(PowerUp::moveOutOfBounds);

        if (new Random().nextInt(150) < 2) {
            enemies.add(new KamikazeEnemy(new Random().nextInt(750), 0));
        }

        if (score >= 100 && new Random().nextInt(250) < 2) {
            enemies.add(new ShooterEnemy(new Random().nextInt(750), 0));
        }

        if (health < 5 && new Random().nextInt(1000) < 2) {
            powerUps.add(new PowerUp(new Random().nextInt(750), 0));
        }

        for (EnemyBase enemy : enemies) {
            enemy.move();
            if (enemy instanceof ShooterEnemy && ((ShooterEnemy) enemy).shouldShoot()) {
                enemyBullets.add(new EnemyBullet(enemy.getX() + 25, enemy.getY() + 50));
            }
        }

        for (Bullet bullet : bullets) bullet.move();
        for (EnemyBullet bullet : enemyBullets) bullet.move();
        for (PowerUp powerUp : powerUps) powerUp.move();

        checkCollisions();
        repaint();
    }

    private void checkCollisions() {
        bullets.removeIf(bullet -> {
            Iterator<EnemyBase> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                EnemyBase enemy = enemyIterator.next();
                if (bullet.collidesWith(enemy)) {
                    enemyIterator.remove(); // Remove the enemy
                    score += 10; // Increase score
                    return true; // Remove the bullet as well
                }
            }
            return false;
        });
    
        enemyBullets.removeIf(bullet -> {
            if (bullet.collidesWith(player)) {
                takeDamage();
                return true;
            }
            return false;
        });
    
        enemies.removeIf(enemy -> {
            if (enemy instanceof KamikazeEnemy && enemy.getBounds().intersects(player.getBounds())) {
                takeDamage();
                return true;
            }
            return false;
        });
    
        powerUps.removeIf(powerUp -> {
            if (powerUp.collidesWith(player)) {
                if (health < 5) health++;
                return true;
            }
            return false;
        });
    }
    
    private void takeDamage() {
        health--;
        if (health <= 0) gameOver();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            togglePause();
        } else if (!paused && health > 0) {
            player.handleKeyPress(e.getKeyCode());
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                bullets.add(new Bullet(player.getX() + 22, player.getY()));
            }
        }

        if (health <= 0 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            restartGame();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
    System.exit(0); // or pause/fullscreen toggle
}

    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.handleKeyRelease(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void togglePause() {
        paused = !paused;
        resumeButton.setVisible(paused);
        restartButton.setVisible(paused);
        repaint();
    }

    private void restartGame() {
        score = 0;
        health = 5;
        bullets.clear();
        enemies.clear();
        enemyBullets.clear();
        powerUps.clear();
        player.setPosition(375, 500);

        initializeGame();
        paused = false;
        resumeButton.setVisible(false);
        restartButton.setVisible(false);
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Shooter");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setUndecorated(true); // removes title bar

GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
GraphicsDevice device = env.getDefaultScreenDevice();

SpaceShooter game = new SpaceShooter();
frame.add(game);
frame.setResizable(false);

device.setFullScreenWindow(frame); // set full screen
game.requestFocusInWindow();

    }

    private void gameOver() {
        health = 0;
        repaint();
        String name = JOptionPane.showInputDialog(null, "Enter your name:");
        if (name != null && !name.trim().isEmpty()) {
            ScoreDAO.saveScore(name.trim(), score, "Normal"); // You can replace "Normal" with a dynamic difficulty variable
        }
    }
}
