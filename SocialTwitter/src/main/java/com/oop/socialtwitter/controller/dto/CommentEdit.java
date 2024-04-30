// CommentEdit.java
package com.oop.socialtwitter.controller.dto;

public class CommentEdit {
    private String commentBody;
    private int commentID;

    // Getters and setters

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
}