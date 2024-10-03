package main.webapp;
import bl.Administrator;

import java.sql.SQLException;
import java.util.Scanner;

public class MainAdmin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Be användaren om inloggningsuppgifter
        System.out.print("Ange användarnamn: ");
        String username = scanner.nextLine();

        System.out.print("Ange lösenord: ");
        String password = scanner.nextLine();

        System.out.print("Ange e-post: ");
        String email = scanner.nextLine();

        // Skapa en Administrator-objekt
        Administrator admin = new Administrator(username, password, email);

        try {
            // Validera administratören
            if (admin.isValidAdministrator()) {
                System.out.println("Administratören är giltig.");
            } else {
                System.out.println("Administrator hittades inte. Lägga den");

                // Försök att lägga till administratören i databasen om de inte är giltiga
                boolean success = admin.addAdministrator();
                if (success) {
                    System.out.println("Administratören har lagts till framgångsrikt.");
                } else {
                    System.out.println("Det gick inte att lägga till administratören. Kontrollera uppgifterna och försök igen.");
                }
            }
        } finally {
            scanner.close(); // Stäng scanner
        }
    }
}


