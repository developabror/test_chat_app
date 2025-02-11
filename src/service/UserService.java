package service;

import db.Database;
import entity.Message;
import entity.User;
import utils.Context;

import java.util.List;
import java.util.Optional;

import static service.AuthService.getUserByEmail;
import static utils.Utill.getInt;
import static utils.Utill.getString;


public class UserService {
    Database database =Database.getInstance();
    MessagingService messagingService = MessagingService.getInstance();
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
                    database.messages.add(newMessage);
                }
                case 2-> {
                    for (String contact : Context.getUser().getContacts()) {
                        System.out.println(contact);
                    }
                    String email = getString("open chat with..?");
                    Optional<User> optionalUser = getUserByEmail(email);
                    if (optionalUser.isEmpty()) {
                        System.out.println("user not found");
                        break;
                    }
                    User user = optionalUser.get();
                    List<Message> messageByUser = messagingService.getMessageByUser(Context.getUser().getId(), user.getId());
                    if (messageByUser == null || messageByUser.isEmpty()) {
                        System.out.println("you have not messages with this user");
                        break;
                    }
                    printMessage(messageByUser,false);
                    editMessage(messageByUser);

                }
            }
        }
    }

    private void editMessage(List<Message> messageByUser) {
        while (true){
            switch (getInt("""
                    0 exit
                    1 edit
                    2 delete
                    """)){
                case 0->{
                    return;
                }
                case 1->{
                    printMessage(messageByUser,true);
                    System.out.println("enter id to edit");
                }
            }
        }
    }

    public void printMessage(List<Message> messageList, boolean hasId){
        for (Message temp : messageList) {
            if (temp.getToId().equals(Context.getUser().getId())){
                temp.setHasRead(true);
                System.out.println(temp.getText());
            }else {
                System.out.println("\t\t\t\t"+temp.getText()+" "+(temp.getHasRead()? "✅":"☑️")+(hasId?temp.getId():""));
            }
        }
    }

    private static UserService userService;
    public static UserService getInstance(){
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

}
