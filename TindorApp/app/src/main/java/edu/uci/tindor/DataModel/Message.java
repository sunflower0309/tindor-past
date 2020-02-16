package edu.uci.tindor.DataModel;

public class Message {
    public String sentBy;
    public String message;
    public String time;

    public Message(String sentBy, String message, String time) {
        this.sentBy = sentBy;
        this.message = message;
        this.time = time;
    }
}
