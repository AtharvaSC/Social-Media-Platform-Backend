//PostResponse.java
package com.oop.socialtwitter.controller.dto;

import java.sql.Date;
import java.util.List;

//output of retrieval an existing post
public class PostResponse {
    private int postID;
    private String postBody;
    private Date date;
    private List<CommentResponse> comments;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
