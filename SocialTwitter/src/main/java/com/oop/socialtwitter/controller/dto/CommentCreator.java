// CommentCreator.java
package com.oop.socialtwitter.controller.dto;

//don't match with create comment , this is for the actual comment creator obj
public class CommentCreator {
    private int userID;
    private String name;

    // Getters and setters

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}