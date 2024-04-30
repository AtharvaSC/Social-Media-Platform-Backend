// CommentService.java
package com.oop.socialtwitter.service;

import com.oop.socialtwitter.controller.dto.*;

public interface CommentService {
    String createComment(CommentRequest commentRequest);
    CommentResponse getComment(int commentID);
    String editComment(CommentEdit commentEdit);
    String deleteComment(int commentID);
}