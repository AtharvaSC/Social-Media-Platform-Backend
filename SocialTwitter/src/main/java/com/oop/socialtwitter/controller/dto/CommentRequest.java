// CommentRequest.java
package com.oop.socialtwitter.controller.dto;

//for creating comment
public class CommentRequest {
    private String commentBody;
    private int postID;
    private int userID;

    // Getters and setters

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}