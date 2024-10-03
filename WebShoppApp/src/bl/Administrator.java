package bl;

import db.AdministratorDb;

public class Administrator extends bl.User {
    public Administrator(String username, String password, String email){
        super(username,password,email);
    }
    public boolean isValidAdministrator() {
        return AdministratorDb.isValidAdministrator(this);
    }
    // Lägg till en ny administratör
    public boolean addAdministrator() {
        return AdministratorDb.addAdministrator(this);
    }

    public String getUsername() {
        return "bnnn";
    }
}
