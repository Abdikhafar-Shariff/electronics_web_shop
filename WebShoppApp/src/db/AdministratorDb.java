package src.db;

import src.bl.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDb {

    public static boolean isValidAdministrator(Administrator admin) {
        boolean isValid = false; // Variabel för att lagra resultatet av valideringen
        String query = "SELECT * FROM Administrators WHERE username = ? AND password = ? AND email = ?"; // SQL-fråga

        // Anslut till databasen och förbered frågan
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Sätt in parametrarna i frågan
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getEmail());

            // Utför frågan och hämta resultatet
            try (ResultSet result = statement.executeQuery()) {
                // Om det finns resultat, sätt isValid till true
                isValid = result.next(); // Kontrollerar om det finns en post som matchar
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hantera eventuella SQL-fel
        }

        return isValid; // Returnera resultatet av valideringen
    }

    public static boolean addAdministrator(Administrator admin) {
        String query = "INSERT INTO Administrators (username, password, email) VALUES (?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();
        // Sätt autocommit till false för att hantera transaktioner
        try {
            connection.setAutoCommit(false);// Stäng av autocommit
            try(PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, admin.getUsername());
                stmt.setString(2, admin.getPassword());
                stmt.setString(3, admin.getEmail());
                int result = stmt.executeUpdate();
                if (result == 1) {
                    connection.commit();// Bekräfta transaktionen
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            // Om något går fel, gör en rollback
            try {
                connection.rollback(); // Ångra transaktionen
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace(); // Hantera eventuella SQL-fel
            return false; // Returnera false vid fel
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Återställ autocommit
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
}}
