package service;

import db.Database;
import entity.Message;
import entity.User;
import utils.Context;

import java.util.Optional;

import static utils.Utill.*;


public class UserService {
    Database database =Database.getInstance();
    public void service() {
        while (true) {
            String message = """
                    0 exit
                    1 write message
                    2 read chat
                    """;
            switch (getInt(message)) {
                case 0->{
                    System.out.println("bye bye");
                    return;
                }
                case 1->{
                    String email = getString("enter email to write");
                    Optional<User> optionalUser = getUserByEmail(email);
                    if (optionalUser.isEmpty()){
                        System.out.println("user not found");
                        return;
                    }
                    User user = optionalUser.get();
                    user.getContacts().add(Context.getUser().getEmail());
                    Context.getUser().getContacts().add(user.getEmail());
                    String text = getString("enter message to write");
                    Message newMessage = new Message();
                    newMessage.setText(text);
                    newMessage.setFromId(Context.getUser().getId());
                    newMessage.setToId(user.getId());
                }
                case 2->{
                    for (String contact : Context.getUser().getContacts()) {
                        System.out.println(contact);
                    }
                    String string = getString("open chat with..?");
                }
            }
        }
    }

    public Optional<User> getUserByEmail(String email) {
        for (User user : database.users) {
            if (user.getEmail().equals(email) && user.getActive()){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
