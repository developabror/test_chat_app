package service;

import db.Database;
import entity.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagingService {
    Database database = Database.getInstance();

    public void saveMessage(Message message) {
        database.messages.add(message);
    }

    public List<Message> getMessageByUser(String userId, String otherId) {
        List<Message> resp = new ArrayList<>();
        for (Message message : database.messages) {
            if (
                    (message.getFromId().equals(userId)
                            && message.getToId().equals(otherId)) ||
                            (message.getFromId().equals(otherId)
                                    && message.getToId().equals(userId))) {
                resp.add(message);
            }
        }
        return resp;
    }
}
