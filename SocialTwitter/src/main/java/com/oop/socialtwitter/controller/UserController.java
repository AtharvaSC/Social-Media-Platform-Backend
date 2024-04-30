package com.oop.socialtwitter.controller;

import com.oop.socialtwitter.controller.dto.*;
import com.oop.socialtwitter.service.PostService;
import com.oop.socialtwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // Implementing the USER FEED endpoint
    @GetMapping("/")
    public ResponseEntity<List<PostResponse>> getAllPostsInReverseChronologicalOrder() {
        List<PostResponse> posts = postService.getAllPostsInReverseChronologicalOrder();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUserDetail(@RequestParam int userID) {
        UserResponse userResponse = userService.getUserDetail(userID);
        if (userResponse != null) {
            return ResponseEntity.ok(userResponse);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objResponse);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
