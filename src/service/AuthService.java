package service;

import db.Database;
import utils.Context;

public class AuthService {
    Database database=Database.getInstance();
    public void signIn() {
        Context.setUser(database.users.get(0));
    }
    public void signUp(){

    }

}
