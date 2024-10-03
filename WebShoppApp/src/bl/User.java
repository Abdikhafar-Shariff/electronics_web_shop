package bl;
import db.UserDb;
import java.sql.SQLException;

public class User {
    private int userId;
    private String username, password,email, address, phoneNumber;
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public boolean isValidUser() throws SQLException {
        return UserDb.isValidUser(this);
    }
    public static void deleterUser(String username, String password, String email) throws SQLException {
        User user = new User(username, password, email);
        if(UserDb.isValidUser(user)){
            UserDb.deleteUser(user);
        }
    }
    // Getters och setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}