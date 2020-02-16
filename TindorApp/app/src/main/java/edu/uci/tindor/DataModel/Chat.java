package edu.uci.tindor.DataModel;

public class Chat {
    public String userId;
    public String chatId;
    public String otherUserId;
    public String name;
    public String imageUrl;

    public Chat(String userId, String chatId, String otherUserId, String name, String imageUrl) {
        this.userId = userId;
        this.chatId = chatId;
        this.otherUserId = otherUserId;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
