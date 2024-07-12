package service;

import db.Database;
import entity.User;
import utils.Context;

import java.util.Optional;

import static utils.Utill.getInt;
import static utils.Utill.getString;

public class AuthService {
    UserService userService =UserService.getInstance();
    static Database database=Database.getInstance();


    public void service(){
        while (true){
            switch (getInt("""
                    0 exit
                    1 sign in
                    2 sign up
                    """)){
                case 0->{
                    System.out.println("bye bye");
                    return;
                }
                case 1->{
                    signIn();
                }
                case 2->{
                    signUp();
                }
            }
        }
    }


    public void signIn() {
        String email = getString("enter email");
        String password = getString("enter password");
        for (User user : database.users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                Context.setUser(user);
                userService.service();
                break;
            }
        }

    }
    public void signUp(){
        User user=new User();
        user.setName(getString("enter name"));
        user.setEmail(getString("enter email"));
        if (getUserByEmail(user.getEmail()).isPresent()) {
            System.out.println("this email already registered, please sign in!");
            return;
        }
        user.setPassword(getString("enter password"));
        database.users.add(user);
    }

    public static Optional<User> getUserByEmail(String email) {
        for (User user : database.users) {
            if (user.getEmail().equals(email) && user.getActive()){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
