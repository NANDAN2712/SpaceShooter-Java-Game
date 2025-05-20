
import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/space_shooter";
    private static final String USER = "root"; // change if needed
    private static final String PASS = "your_password"; // change this to your actual MySQL password

    public static void insertScore(String name, int score) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String query = "INSERT INTO scores (name, score) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, score);
            stmt.executeUpdate();
            System.out.println("✅ Score saved for " + name);
        } catch (SQLException e) {
            System.out.println("❌ DB Error: " + e.getMessage());
        }
    }
}
