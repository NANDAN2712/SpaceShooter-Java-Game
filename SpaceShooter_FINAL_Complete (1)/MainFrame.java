
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {

        setTitle("Space Shooter - Launcher");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Space Shooter", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        JLabel nameLabel = new JLabel("Enter Your Name:", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JTextField nameField = new JTextField();
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));

        centerPanel.add(nameLabel);
        centerPanel.add(nameField);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 22));
        centerPanel.add(startButton);
        JButton viewScoresBtn = new JButton("View Scores");
        viewScoresBtn.setFont(new Font("Arial", Font.PLAIN, 22));
        viewScoresBtn.addActionListener(e -> new ScoreViewer());
        centerPanel.add(viewScoresBtn);
        add(centerPanel, BorderLayout.CENTER);

        startButton.addActionListener(e -> {
            String playerName = nameField.getText().trim();
            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name.");
                return;
            }

            savePlayerName(playerName); // store to file

            dispose(); // close launcher
            JFrame gameFrame = new JFrame("Space Shooter - Player: " + playerName);
            SpaceShooter gamePanel = new SpaceShooter();
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.getContentPane().add(gamePanel);
            gameFrame.pack();
            gameFrame.setResizable(false);
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
            gamePanel.requestFocusInWindow();
        });
    }

    private void savePlayerName(String name) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("player.txt"))) {
            writer.println(name);
        } catch (IOException e) {
            System.out.println("Error saving player name.");
        }
    }

    public static void main(String[] args) {ScoreDAO.initializeDatabase();
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}