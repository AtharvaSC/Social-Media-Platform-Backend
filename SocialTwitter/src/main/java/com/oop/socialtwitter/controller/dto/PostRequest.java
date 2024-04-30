//PostRequest.java
package com.oop.socialtwitter.controller.dto;

//for creating post
public class PostRequest {
    private String postBody;
    private int userID;

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
