//PostEdit.java
package com.oop.socialtwitter.controller.dto;

//for editing a post
public class PostEdit {
    private String postBody;
    private int postID;

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
}
