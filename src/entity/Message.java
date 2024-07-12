package entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {
    private final String id = UUID.randomUUID().toString();
    private String text;
    private String fromId;
    private String toId;
    private final LocalDateTime time = LocalDateTime.now();
    private Boolean hasRead = false;

    public Message(){

    }
    public Message(String text, String fromId, String toId, Boolean hasRead) {
        this.text = text;
        this.fromId = fromId;
        this.toId = toId;
        this.hasRead = hasRead;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", time=" + time +
                ", hasRead=" + hasRead +
                '}';
    }
}
