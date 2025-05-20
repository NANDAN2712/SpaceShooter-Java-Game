import java.sql.*;

public class DBConnection {
    public static Connection connect() {
        try {
            String url = "jdbc:sqlite:scores.db"; // SQLite DB file in root folder
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
            return null;
        }
    }
}