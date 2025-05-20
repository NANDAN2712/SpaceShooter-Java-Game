import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ScoreViewer extends JFrame {

    public ScoreViewer() {
        setTitle("High Scores");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Name", "Score", "Difficulty", "Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM scores ORDER BY score DESC")) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("player_name"),
                    rs.getInt("score"),
                    rs.getString("difficulty"),
                    rs.getString("played_on")
                };
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading scores: " + e.getMessage());
        }

        add(new JScrollPane(table));
        setVisible(true);
    }
}