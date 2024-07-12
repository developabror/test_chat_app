package utils;

import entity.User;

public class Context {
    private static User user;
    public static User getUser() {
        return user;
    }
    public static void setUser(User currentUser) {
        user=currentUser;
    }
}

