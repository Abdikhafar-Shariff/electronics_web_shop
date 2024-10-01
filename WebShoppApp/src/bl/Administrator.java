package src.bl;

import src.db.AdministratorDb;

public class Administrator extends User{
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
}
