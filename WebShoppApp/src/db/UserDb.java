package db;

import db.DatabaseConnection;
import bl.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDb {
    public static boolean isValidUser(User user) throws SQLException {
        boolean isValid= false;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Users WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();

            // Om resultSet innehåller resultat, då finns användaren och vi jämför uppgifterna
            if (resultSet.next()) {
                String dbEmail = resultSet.getString("email");
                String dbPassword = resultSet.getString("password");

                // Jämför den hämtade e-posten och lösenordet med användarens inmatade data
                if (dbEmail.equals(user.getEmail()) && dbPassword.equals(user.getPassword())) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error checking user validity", e);
        } finally {
            // Stäng ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Stäng PreparedStatement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Stäng Connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isValid;
    }
    public static boolean isUserRegistered(User user) {
        boolean isRegistered = false;
        String query = "SELECT * FROM Users WHERE email = ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Skapa och använd anslutningen
            connection = DatabaseConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getEmail());

            // Exekvera frågan och hantera resultat
            rs = stmt.executeQuery();
            if (rs.next()) {
                isRegistered = true; // Användare hittad
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Hantera SQL-fel

        } finally {
            // Stäng alla resurser
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return isRegistered;
    }
    // Hämta alla användare
    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {
            // Iterera genom resultatet och skapa User-objekt
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
                users.add(user); // Lägg till användaren i listan
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving users", e);
        }

        return users; // Returnera listan med användare
    }
    public static void AddNewUser(User user) throws SQLException {
        // Kontrollera om användaren redan finns
     if (isUserRegistered(user)) {
           System.out.println("User already exists");
           return;
     }
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO users (user_id, username, password, email) VALUES (?, ?, ?, ?)";

        PreparedStatement addNewUser = null;
        try {
            connection.setAutoCommit(false); // Startar en transaktion
            addNewUser = connection.prepareStatement(query);

            addNewUser.setInt(1, user.getUserId());
            addNewUser.setString(2, user.getUsername());
            addNewUser.setString(3, user.getPassword());
            addNewUser.setString(4, user.getEmail());

            int result = addNewUser.executeUpdate();
            connection.commit(); // Utför transaktionen

            if (result == 1) {
                System.out.println("User successfully registered.");
            } else {
                System.out.println("Failed to register user. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); // Återställer om något går fel
                    System.out.println("Transaction rolled back");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (addNewUser != null) {
                addNewUser.close(); // Stänger statement
            }
            if (connection != null) {
                connection.setAutoCommit(true); // Återställ till standardläge
            }
        }
    }
    public static void deleteUser(User user) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM users WHERE email = ? AND password = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());

            // Sätt autocommit till false för att hantera transaktioner manuellt
            connection.setAutoCommit(false);
            int result = stmt.executeUpdate();

            // Om allt gick bra, gör en commit
            connection.commit();
            connection.setAutoCommit(true); // Återställ autocommit

            if (result == 1) {
                System.out.println("Successfully removed the user");
            } else {
                System.out.println("No user was removed, possibly does not exist or invalid credentials");
            }

        } catch (SQLException ex) {
            // Om något går fel, gör en rollback
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            // Stäng anslutningen om den inte är stängd
            if (connection != null) {
                connection.close();
            }
        }
    }
}