package src;

import src.bl.User;
import src.db.UserDb;

import java.sql.SQLException;

public class MainUser {
    public static void main(String[] args) {
        try {
            UserDb userDb = new UserDb(); // Skapa en instans av UserDb
            // Test database connection
            System.out.println("Testing database connection...");

            // Create a new user
            User newUser = new User("testuser", "password123", "testuser@example.com");
            UserDb.AddNewUser(newUser); // Attempt to add the new user

            User user2 = new User("enaamAlSbini", "12345", "enaam.sb@gmail.com");
            UserDb.AddNewUser(user2); // Attempt to add the new user
            // Validate user
            if (newUser.isValidUser()) {
                System.out.println("User is valid.");
            } else {
                System.out.println("User is invalid.");
            }
            if (user2.isValidUser()) {
                System.out.println("User is valid.");
            } else {
                System.out.println("User is invalid.");
            }
            // Delete user
            User.deleterUser("testuser", "password123", "testuser@example.com");

            // List all users
            System.out.println("All users:");
            for (User user : UserDb.getAllUsers()) {
                System.out.println(user.getUsername());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
