// PostController.java
package com.oop.socialtwitter.controller;

import com.oop.socialtwitter.controller.dto.*;
import com.oop.socialtwitter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        String response = postService.createPost(postRequest);
        if (response.equals("Post created successfully")) {
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", response);
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(objResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> getPost(@RequestParam int postID) {
        PostResponse post = postService.getPost(postID);

        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(objResponse);
        }
    }

    @PatchMapping
    public ResponseEntity<?> editPost(@RequestBody PostEdit postEdit) {
        String response = postService.editPost(postEdit);
        if (response.equals("Post edited successfully")) {
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", response);
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(objResponse);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam int postID) {
        String response = postService.deletePost(postID);
        if (response.equals("Post deleted")) {
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", response);
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(objResponse);
        }
    }
}
