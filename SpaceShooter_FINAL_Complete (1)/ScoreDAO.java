import java.sql.*;

public class ScoreDAO {
    public static void saveScore(String name, int score, String difficulty) {
        String sql = "INSERT INTO scores(player_name, score, difficulty, played_on) VALUES (?, ?, ?, datetime('now'))";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, score);
            stmt.setString(3, difficulty);
            stmt.executeUpdate();
            System.out.println("Score saved!");
        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
        }
    }

    public static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS scores (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "player_name TEXT NOT NULL," +
                     "score INTEGER NOT NULL," +
                     "difficulty TEXT," +
                     "played_on TEXT)";
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Table Creation Error: " + e.getMessage());
        }
    }

}