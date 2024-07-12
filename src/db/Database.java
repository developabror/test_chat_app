package db;

import entity.Message;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<Message> messages = new ArrayList<>();
    public List<User> users = new ArrayList<>();

    public static Database database;
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }
}
