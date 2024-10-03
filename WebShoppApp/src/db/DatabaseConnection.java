package src.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String dbName = "webshop";
    private String jdbcUrl = "jdbc:mysql://localhost:3306/" + dbName;
    private String username = "root";
    private String password = "12345";
    private Connection connection = null;

    // Skapa en instans av databaskopplingen
    private static DatabaseConnection getInstance() {
        return new DatabaseConnection();
    }

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Metod för att hämta anslutningen
    public static Connection getConnection() {
        return getInstance().connection;
    }

    // Metod för att stänga anslutningen
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}