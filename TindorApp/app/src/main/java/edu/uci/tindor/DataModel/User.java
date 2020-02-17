package edu.uci.tindor.DataModel;

public class User {
    static User currentUser;
    static User getCurrentUser() {
        return currentUser;
    }

    public String uid = "";
    public String name = "";
    public String age = "";
    public String email = "";
    public String gender = "";
    public String preferredGender = "";
    public String imageUrl = "";
}
