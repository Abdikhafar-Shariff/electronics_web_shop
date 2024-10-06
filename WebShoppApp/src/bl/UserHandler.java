package bl;

import db.UserDb;
import ui.UserInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserHandler {

    public static User getUserByCredentials(UserInfo userInfo) throws SQLException {
        User user = new User(userInfo.getUserId(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail());
        // Convert UserInfo to User model for validation
        return UserDb.getUserByCredentials(user);
    }

    // Check if the user is valid by interacting with UserDb
    public static boolean isValidUser(UserInfo userInfo) throws SQLException {
        // Convert UserInfo to User model for validation
        User user = new User(userInfo.getUserId(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail());

        // Check validity in UserDb
        return UserDb.isValidUser(user);
    }

    public static boolean isUserRegistered(UserInfo userInfo){
        // Convert UserInfo to User model for validation
        User user = new User(userInfo.getUserId(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail());

        return UserDb.isUserRegistered(user);

    }

    // Delete user by interacting with UserDb
    public static void deleteUser(UserInfo userInfo) throws SQLException {
        // Convert UserInfo to User model
        User user = new User(userInfo.getUserId(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail());

        // Check if the user is valid, then delete if found
        if (UserDb.isValidUser(user)) {
            UserDb.deleteUser(user);
        } else {
            System.out.println("Invalid user, deletion aborted.");
        }
    }

    // Get all users
    public static List<UserInfo> getAllUsers() throws SQLException {
        // get the list of items from the database
        List<User> userList = UserDb.getAllUsers();

        List<UserInfo> userInfoList = new ArrayList<>();

        //// Loop through each User and convert it into an ItemInfo object
        for(User user: userList){
            UserInfo userInfo = new UserInfo(
                    user.getUserId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword()

            );
            // Add the converted ItemInfo to the list
            userInfoList.add(userInfo);
        }
         return userInfoList;
    }

        // Add a new user by converting UserInfo to User and calling the database method
        public static void addNewUser(UserInfo userInfo) throws SQLException {
            // Convert UserInfo to User model
            User newUser = new User(userInfo.getUserId(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail());

            // Pass the User object to the UserDb to insert into the database
            UserDb.AddNewUser(newUser);
        }


}
