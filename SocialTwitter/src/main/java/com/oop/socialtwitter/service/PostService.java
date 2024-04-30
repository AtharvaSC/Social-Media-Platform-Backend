//PostService.java
package com.oop.socialtwitter.service;

import com.oop.socialtwitter.controller.dto.PostEdit;
import com.oop.socialtwitter.controller.dto.PostRequest;
import com.oop.socialtwitter.controller.dto.PostResponse;

import java.util.List;

public interface PostService {
    String createPost(PostRequest postRequest);
    PostResponse getPost(int postID);
    String editPost(PostEdit postEdit);
    String deletePost(int postID);

    List<PostResponse> getAllPostsInReverseChronologicalOrder();
}
